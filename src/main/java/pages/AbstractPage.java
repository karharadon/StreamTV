package pages;

import data.WrestlerData;
import helper.ScreenShot;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {
    protected WebDriver driver;
    protected String className = this.getClass().getSimpleName();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clearAndSendKeys(WebElement webElement, String text){
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void clearAndSendKeys(WebElement webElement, int text){
        webElement.clear();
        webElement.sendKeys(Integer.toString(text));
    }

    public void waitWhenClickable(WebElement element, int timeSec){
        (new WebDriverWait(driver, timeSec)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void selectFromDD(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }


    void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public void captureScreen(String fileName){
        ScreenShot screenShot = new ScreenShot(driver);
        screenShot.captureScreen(fileName);
    }

}
