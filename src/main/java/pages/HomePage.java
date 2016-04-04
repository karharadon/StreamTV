package pages;

import data.WrestlerData;
import helper.ConfigProperties;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomePage extends AbstractPage {

    public WrestlerData wrestlerData5 = new WrestlerData("Ukraina","Francuana","12-05-1989","Maroccana","Volynska","Kyiv",
            "Dinamo",
            "Kolos","Joda","Palladin","FW","Cadet","2016","Recieved");

    public WrestlerData wrestlerDataforUpdate = new WrestlerData("Britana","Indiana","12-05-1979","Kanadiana","Kyiv",
            "Zaporizka","Kolos",
            "SK","ObiVan","DartaMol","FS","Senior","2017","Produced");

    public WrestlerData wrestlerData3 = new WrestlerData("Britana","Indiana","12-05-1979","Kanadiana","Kyiv",
            "Zaporizka","Kolos",
            "SK","ObiVan","DartaMol","FS","Senior","2017","Produced");

    public WrestlerData wrestlerData4 = new WrestlerData("Britana","Indiana","12-05-1979","Kanadiana","Kyiv",
            "Zaporizka","Kolos",
            "SK","ObiVan","DartaMol","FS","Senior","2017","Produced");

    public WrestlerData wrestlerData = new WrestlerData("Britana","Indiana","12-05-1979","Kanadiana","Kyiv",
            "Zaporizka","Kolos",
            "SK","ObiVan","DartaMol","FS","Senior","2017","Produced");

    ArrayList<String> assertionErrors = new ArrayList();

    String wrestlerFullName = wrestlerData.lastName + " " + wrestlerData.firstName + " " + wrestlerData.middleName;
    String wrestlerFullUpdateName = wrestlerDataforUpdate.lastName + " " + wrestlerDataforUpdate.firstName + " " +
            wrestlerDataforUpdate.middleName;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private Date today = Calendar.getInstance().getTime();
    private String sysDate = dateFormat.format(today);

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
        createWrestler();
        findWrestler();
        verifySearchResultWithCodeData();
        verifyProfileDataWithCode();
        updateWrestler();
        deleteWrestler();
    }

    public void fillAllFields(WrestlerData wrestler) {
        this.wrestlerData = wrestler;
        clearAndSendKeys(fieldLastName, wrestler.lastName);
        clearAndSendKeys(fieldFirstName, wrestler.firstName);
        clearAndSendKeys(fieldDateOfBirth, wrestler.dateOfBirth);
        clearAndSendKeys(fieldMiddleName, wrestler.middleName);
        selectFromDD(fieldRegion1, wrestler.regionFirst);
        selectFromDD(fieldRegion2, wrestler.regionSecond);
        selectFromDD(fieldFST1, wrestler.fstFirst);
        selectFromDD(fieldFST2, wrestler.fstSecond);
        clearAndSendKeys(fieldTrainer1, wrestler.trainerFirst);
        clearAndSendKeys(fieldTrainer2, wrestler.trainerSecond);
        selectFromDD(fieldStyle, wrestler.style);
        selectFromDD(fieldAge, wrestler.age);
        selectFromDD(fieldYear, wrestler.year);
        selectFromDD(fieldCard, wrestler.card);
    }

    public void createWrestler(){
        buttonCreateNewWrestler.click();
        fillAllFields(wrestlerData);
        buttonSave.click();

        //TODO org.openqa.selenium.ElementNotVisibleException: element not visible
        waitWhenClickable(closeFirstPage,7);
        closeFirstPage.click();
    }

    private void findWrestler() {
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
    }

    private void verifySearchResultWithCodeData() {
        assertationProfileDataWithCode0(wrestlerFIO, wrestlerFullName);
        assertationProfileDataWithCode0(wrestlerRegion, wrestlerData.regionFirst);
        assertationProfileDataWithCode0(wrestlerFST, wrestlerData.fstFirst);
        assertationProfileDataWithCode0(wrestlerLicense, wrestlerData.year);
        assertationProfileDataWithCode0(wrestlerPhoto, "No");
        assertationProfileDataWithCode0(wrestlerStyle, wrestlerData.style);
        assertationProfileDataWithCode0(wrestlerChanged, sysDate);
    }

    private void assertationProfileDataWithCode0(WebElement fact, String expected) {
        try {
            assert (fact.getText().equals(expected));
        } catch (AssertionError e) {
            assertionErrors.add("The field " + " expected contains " + expected
                    + " but was: " + fact.getText());
        }
    }

    private void assertationProfileDataWithCode(WebElement fact, String expected) {
        try {
            assert (getComboboxElement(fact).equals(expected));
        } catch (AssertionError e) {
            assertionErrors.add("The field " + " expected contains " + expected
                    + " but was: " + getComboboxElement(fact));
        }
    }

    private void assertationProfileDataWithCode2(WebElement fact, String expected) {
        try {
            assert (fact.getAttribute("value").equals(expected));
        } catch (AssertionError e) {
            assertionErrors.add("The field " + " expected contains " + expected
                    + " but was: " + fact.getAttribute("value"));
        }
    }

    private void verifyProfileDataWithCode(){
        wrestlerFIO.click();
        assertationProfileDataWithCode2(fieldLastName, wrestlerData.lastName);
        assertationProfileDataWithCode2(fieldFirstName, wrestlerData.firstName);
        assertationProfileDataWithCode2(fieldDateOfBirth, wrestlerData.dateOfBirth);
        assertationProfileDataWithCode2(fieldMiddleName, wrestlerData.middleName);
        assertationProfileDataWithCode(fieldRegion1, wrestlerData.regionFirst);
        assertationProfileDataWithCode(fieldFST1, wrestlerData.fstFirst);
        assertationProfileDataWithCode(fieldFST2, wrestlerData.fstSecond);
        assertationProfileDataWithCode2(fieldTrainer1, wrestlerData.trainerFirst);
        assertationProfileDataWithCode2(fieldTrainer2, wrestlerData.trainerSecond);
        assertationProfileDataWithCode(fieldStyle, wrestlerData.style);
        assertationProfileDataWithCode(fieldAge, wrestlerData.age);
        assertationProfileDataWithCode(fieldYear, wrestlerData.year);
        assertationProfileDataWithCode(fieldCard, wrestlerData.card);

        //TODO make 1 method
        waitWhenClickable(closeFirstPage,7);
        closeFirstPage.click();

        for (int i = 0; i < assertionErrors.size(); i++) {
            System.out.println(assertionErrors.get(i));
        }
    }

    private void updateWrestler() {
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
        wrestlerFIO.click();
        fillAllFields(wrestlerDataforUpdate);
        buttonSave.click();

        //TODO make 2 method, not 4, element not visible
        waitWhenClickable(closeFirstPage,7);
        closeFirstPage.click();
        waitWhenClickable(wrestlerPage,7);
        wrestlerPage.click();

        clearAndSendKeys(fieldSearchFor, wrestlerFullUpdateName);
        buttonSearchFor.click();

        assertThat("The first row expected contains " + wrestlerFullUpdateName + " but was: " + wrestlerFIO.getText(),
               wrestlerFIO.getText().equals(wrestlerFullUpdateName));
    }

    private void deleteWrestler() {
        clearAndSendKeys(fieldSearchFor, wrestlerFullUpdateName);
        buttonSearchFor.click();
        wrestlerFIO.click();
        deleteWrestler.click();
        deleteСonfirm.click();
        clearAndSendKeys(fieldSearchFor, wrestlerFullUpdateName);
        buttonSearchFor.click();

       try{
           assertThat("The first row expected contains \"NULL\" but was: " + wrestlerFIO.getText(),
                   wrestlerFIO.getText().equals(null));
        } catch (NoSuchElementException e) {

        } catch (Exception b){
           //TODO Add to log
           captureScreen(className);
           b.printStackTrace();
           driver.close();
        }
       }

    public void createFewWrestlers() {


    }

    public void checkFilters() {
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
    public WebElement wrestlerFIO;

    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    public WebElement wrestlerRegion;

    @FindBy(xpath = "//tbody/tr[1]/td[4]")
    public WebElement wrestlerFST;

    @FindBy(xpath = "//tbody/tr[1]/td[5]")
    public WebElement wrestlerLicense;

    @FindBy(xpath = "//tbody/tr[1]/td[6]")
    public WebElement wrestlerPhoto;

    @FindBy(xpath = "//tbody/tr[1]/td[7]")
    public WebElement wrestlerStyle;

    @FindBy(xpath = "//tbody/tr[1]/td[8]")
    public WebElement wrestlerChanged;

    @FindBy(xpath = "//div/button[@ng-click=\"delete()\"]")
    public WebElement deleteWrestler;

    @FindBy(xpath = "//button[@ng-click=\"ok()\"]")
    public WebElement deleteСonfirm;

    @FindBy(xpath = "//li[2]//div//span")
    public WebElement closeFirstPage;

    //TODO maybeNotNeedElement
    @FindBy(xpath = "//li[2]//div[1]")
    public WebElement clickFirstPage;
}
