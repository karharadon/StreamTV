package pages;

import data.WrestlerData;
import helper.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomePage extends AbstractPage {

    WrestlerData wrestlerData = new WrestlerData("Dart","Weider","12-05-1989","Skajvoker","Volynska","Kyiv","Dinamo",
            "Kolos","Joda","Palladin","FS","Senior","2017","Recieved");
    String wrestlerFullName = wrestlerData.lastName + " " + wrestlerData.firstName + " " + wrestlerData.middleName;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void login(WebDriver driver) {
        open(driver);
        clearAndSendKeys(fieldLogin, ConfigProperties.getProperty("username"));
        clearAndSendKeys(fieldPassword, ConfigProperties.getProperty("password"));
        buttonLogin.click();
    }


    public void open(WebDriver driver) {
        driver.get(ConfigProperties.getProperty("url"));
        waitForPageLoad(driver);
    }

    public void wrestlerCRUD() {

        createNewWrestler();
        findWrestler();
        verifyWrestler();
        updateWrestler();
        deleteWrestler();
    }


    public void createNewWrestler() {

        buttonCreateNewWrestler.click();
        clearAndSendKeys(fieldLastName,wrestlerData.lastName);
        clearAndSendKeys(fieldFirstName,wrestlerData.firstName);
        clearAndSendKeys(fieldDateOfBirth,wrestlerData.dateOfBirth);
        clearAndSendKeys(fieldMiddleName,wrestlerData.middleName);
        selectFromDD(fieldRegion1,wrestlerData.regionFirst);
        selectFromDD(fieldRegion2,wrestlerData.regionSecond);
        selectFromDD(fieldFST1,wrestlerData.fstFirst);
        selectFromDD(fieldFST2,wrestlerData.fstSecond);
        clearAndSendKeys(fieldTrainer1,wrestlerData.trainerFirst);
        clearAndSendKeys(fieldTrainer2,wrestlerData.trainerSecond);
        selectFromDD(fieldStyle,wrestlerData.style);
        selectFromDD(fieldAge,wrestlerData.age);
        selectFromDD(fieldYear,wrestlerData.year);
        selectFromDD(fieldCard,wrestlerData.card);
        buttonSave.click();
    }

    private void findWrestler() {
        wrestlerPage.click();
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
    }

    private void verifyWrestler() {
        assertThat("The first row expected contains " + wrestlerFullName + " but was: " + firstWrestlerInSearch.getText(),
                firstWrestlerInSearch.getText().equals(wrestlerFullName));
    }

    private void updateWrestler() {
    }

    private void deleteWrestler() {
    }


    @FindBy(xpath = "//div/input [1]")
    public WebElement fieldLogin;

    @FindBy(xpath = "//div/input [@placeholder = 'Password']")
    public WebElement fieldPassword;

    @FindBy(xpath = "//button")
    public WebElement buttonLogin;

    @FindBy(xpath = "//button[2]")
    public WebElement buttonCreateNewWrestler;

    @FindBy(xpath = "//div/input[@placeholder='Last name']")
    public WebElement fieldLastName;

    @FindBy(xpath = "//div/input[@placeholder='First name']")
    public WebElement fieldFirstName ;

    @FindBy(xpath = "//div/input[@placeholder='Date of Birth']")
    public WebElement fieldDateOfBirth;

    @FindBy(xpath = "//div/input[@placeholder='Middle name']")
    public WebElement fieldMiddleName;

    @FindBy(xpath = "//fg-select [@value=\"wr.region1\"]//select")
    public WebElement fieldRegion1;

    @FindBy(xpath = "//fg-select [@value=\"wr.region2\"]//select")
    public WebElement fieldRegion2;

    @FindBy(xpath = "//fg-select [@value=\"wr.fst1\"]//select")
    public WebElement fieldFST1;

    @FindBy(xpath = "//fg-select [@value=\"wr.fst2\"]//select")
    public WebElement fieldFST2;

    @FindBy(xpath = "//fg-typeahead [@value=\"wr.trainer1\"]//input")
    public WebElement fieldTrainer1;

    @FindBy(xpath = "//fg-typeahead [@value=\"wr.trainer2\"]//input")
    public WebElement fieldTrainer2;

    @FindBy(xpath = "//fg-select [@value=\"wr.style\"]//select")
    public WebElement fieldStyle;

    @FindBy(xpath = "//fg-select [@value=\"wr.lictype\"]//select")
    public WebElement fieldAge;

    @FindBy(xpath = "//fg-select [@value=\"wr.expires\"]//select")
    public WebElement fieldYear;

    @FindBy(xpath = "//f-select [@value=\"wr.card_state\"]//select")
    public WebElement fieldCard;

    @FindBy(xpath = "//div[@class=\"button\"][1]/button")
    public WebElement buttonSave;

    @FindBy(xpath = "//div[@class=\"tab-heading\"]")
    public WebElement wrestlerPage;

    @FindBy(xpath = "//input[@ng-model=\"searchFor\"]")
    public WebElement fieldSearchFor;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    public WebElement buttonSearchFor;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement firstWrestlerInSearch;

}
