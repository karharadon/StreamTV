package lastochkin.streamTV.pages;

import lastochkin.streamTV.helper.ScreenShot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public abstract class AbstractPage {
    protected WebDriver driver;
    protected String className = this.getClass().getSimpleName();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clearAndSendKeys(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void waitWhenClickableAndClick(WebElement element, int timeSec) {
        (new WebDriverWait(driver, timeSec)).until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (WebDriverException e) {
            javaScriptHelpsClickOnButton(element);
            e.getMessage();
            e.getAdditionalInformation();
            e.getBuildInformation();
            e.getSystemInformation();
            System.out.println("JavaScript helped click button");
        }
    }

    //helps when:WebDriverExeption. Another element would receive a click
    protected void javaScriptHelpsClickOnButton(WebElement webElement) {
        try {
            ((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
                    + "evt.initMouseEvent('click',true, " + "true, " + "window, 0, 0, 0, 0, 0, false, false, false," +
                    " false, 0,null);" + "arguments[0].dispatchEvent(evt);", webElement);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can not click on button. Java Script didn't help.");
        }
    }

    //fill DD
    public void selectFromDD(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    //take already filled instance from DD
    public String getComboboxElement(WebElement webElement) {
        Select comboBox = new Select(webElement);
        return comboBox.getFirstSelectedOption().getText();
    }

    void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").
                        equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public void captureScreen(String fileName) {
        ScreenShot screenShot = new ScreenShot(driver);
        screenShot.captureScreen(fileName);
    }

    protected void assertSearchResulstWithCode(WebElement fact, String expected, ArrayList<String> err) {
        try {
            assert (fact.getText().equals(expected));
            System.out.println("Assertation search results with code data");
        } catch (AssertionError e) {
            err.add("After search result the field " + " expected contains " + expected
                    + " but was: " + fact.getText());
        }
    }

    //method used for drop fields
    protected void assertProfileDataWithCode(WebElement fact, String expected, ArrayList<String> err) {
        try {
            assert (getComboboxElement(fact).equals(expected));
            System.out.println("Assertation profile data with code data");
        } catch (AssertionError e) {
            err.add("On the profile page the field " + " expected contains " + expected
                    + " but was: " + getComboboxElement(fact));
        }
    }

    //method used for simple fields
    protected void assertProfileDataWithCode2(WebElement fact, String expected, ArrayList<String> errors) {
        try {
            assert (fact.getAttribute("value").equals(expected));
            System.out.println("Assertation profile data with code data");
        } catch (AssertionError e) {
            errors.add("On the profile page the field" + " expected contains " + expected
                    + " but was: " + fact.getAttribute("value"));
        }
    }

    public void checkFilter(List<WebElement> list, String filter) {
        for (WebElement aList : list) {
            assertThat("The field " + filter + " expected " + filter + "but was: " + aList.getText(),
                    aList.getText().equals(filter));
            System.out.println("Filter was checked.");
        }
    }
}