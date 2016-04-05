package pages;

import data.WrestlerData;
import helper.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WrestlerData wrestler1 = new WrestlerData("Britana","Indiana","12-05-1979","Kanadiana","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");
    public WrestlerData wrestler2 = new WrestlerData("Karolina","Italiana","12-05-1989","Karavana","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");
    public WrestlerData wrestler3 = new WrestlerData("Loreleja","Francuana","12-05-1991","Maroccana","Volynska",
            "Kyiv","Dinamo","Kolos","Joda","Palladin","FW","Cadet","2016","Recieved");
    public WrestlerData wrestler4 = new WrestlerData("Antarktida","Penelopa","12-05-1985","Izabella","Odeska",
            "Zaporizka","Kolos", "SK","ObiVan","DartaMol","FS","Senior","2017","Produced");
    public WrestlerData wrestler5 = new WrestlerData("Ukrainiana","Karavana","12-05-1993","Banana","Zakarpatska",
            "Zaporizka","Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");

    String wrestlerFullName1 = wrestler1.lastName + " " + wrestler1.firstName + " " + wrestler1.middleName;
    String wrestlerFullName2 = wrestler2.lastName + " " + wrestler2.firstName + " " + wrestler2.middleName;
    String wrestlerFullName3 = wrestler3.lastName + " " + wrestler3.firstName + " " + wrestler3.middleName;
    String wrestlerFullName4 = wrestler4.lastName + " " + wrestler4.firstName + " " + wrestler4.middleName;
    String wrestlerFullName5 = wrestler5.lastName + " " + wrestler5.firstName + " " + wrestler5.middleName;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private Date today = Calendar.getInstance().getTime();
    private String sysDate = dateFormat.format(today);

    ArrayList<String> errors = new ArrayList<>();

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
        createWrestler(wrestler1);
        findWrestler(wrestlerFullName1);
        verifySearchResultWithCodeData();
        verifyProfileDataWithCode();
        updateWrestler();
        deleteWrestler(wrestlerFullName2);
    }

    public void fillAllFields(WrestlerData wrestler) {
        this.wrestler1 = wrestler;
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
        selectFromDD(fieldYear, wrestler.license);
        selectFromDD(fieldCard, wrestler.card);
    }

    public void createWrestler(WrestlerData wrestler){
        buttonCreateNewWrestler.click();
        fillAllFields(wrestler);
        buttonSave.click();
        waitWhenClickableAndClick(closeProfilePage,7);
    }

    private void findWrestler(String wrestlerFullName) {  //TODO choose last created wrestler, check deletion with last
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
    }

    private void verifySearchResultWithCodeData() { //TODO Fail. Element not found.Make try catch
        assertationProfileDataWithCode0(wrestlerFIO, wrestlerFullName1, errors);
        assertationProfileDataWithCode0(wrestlerRegion, wrestler1.regionFirst, errors);
        assertationProfileDataWithCode0(wrestlerFST, wrestler1.fstFirst, errors);
        assertationProfileDataWithCode0(wrestlerLicense, wrestler1.license, errors);
        assertationProfileDataWithCode0(wrestlerPhoto, "No", errors);
        assertationProfileDataWithCode0(wrestlerStyle, wrestler1.style, errors);
        assertationProfileDataWithCode0(wrestlerChanged, sysDate, errors);
    }

    private void verifyProfileDataWithCode(){
        wrestlerFIO.click();
        assertationProfileDataWithCode2(fieldLastName, wrestler1.lastName, errors);
        assertationProfileDataWithCode2(fieldFirstName, wrestler1.firstName, errors);
        assertationProfileDataWithCode2(fieldDateOfBirth, wrestler1.dateOfBirth, errors);
        assertationProfileDataWithCode2(fieldMiddleName, wrestler1.middleName, errors);
        assertationProfileDataWithCode(fieldRegion1, wrestler1.regionFirst, errors);
        assertationProfileDataWithCode(fieldRegion2, wrestler1.regionSecond, errors);
        assertationProfileDataWithCode(fieldFST1, wrestler1.fstFirst, errors);
        assertationProfileDataWithCode(fieldFST2, wrestler1.fstSecond, errors);
        assertationProfileDataWithCode2(fieldTrainer1, wrestler1.trainerFirst, errors);
        assertationProfileDataWithCode2(fieldTrainer2, wrestler1.trainerSecond, errors);
        assertationProfileDataWithCode(fieldStyle, wrestler1.style, errors);
        assertationProfileDataWithCode(fieldAge, wrestler1.age, errors);
        assertationProfileDataWithCode(fieldYear, wrestler1.license, errors);
        assertationProfileDataWithCode(fieldCard, wrestler1.card, errors);
        waitWhenClickableAndClick(closeProfilePage,7);

        errors.forEach(System.out::println);
    }

    private void updateWrestler() {             //TODO Check updating
        findWrestler(wrestlerFullName1);
        wrestlerFIO.click();
        fillAllFields(wrestler2);
        buttonSave.click();
        waitWhenClickableAndClick(closeProfilePage,7);
        waitWhenClickableAndClick(wrestlerPage,7);
        findWrestler(wrestlerFullName2);

        assertThat("After update the first row expected contains " + wrestlerFullName2 + " but was: "
                + wrestlerFIO.getText(), wrestlerFIO.getText().equals(wrestlerFullName2));
    }

    private void deleteWrestler(String wrestlerFullName) {
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
        wrestlerFIO.click();
        deleteWrestler.click();
        deleteСonfirm.click();
        waitWhenClickableAndClick(fieldSearchFor,7);
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
        checkDeletion(wrestlerFIO);
    }

    public void createFewWrestlers() {
        createWrestler(wrestler1);
        createWrestler(wrestler2);
        createWrestler(wrestler3);
        createWrestler(wrestler4);
        createWrestler(wrestler5);
    }

    public void useFilters() {
        clearAndSendKeys(fieldSearchFor,wrestlerFullName1);
        selectFromDD(filterRegion,wrestler1.regionFirst);
        selectFromDD(filterFST,wrestler1.fstFirst);
        selectFromDD(filterLicense,wrestler1.license);
        selectFromDD(filterPhoto,"No");
        selectFromDD(filterStyle,wrestler1.style);
        selectFromDD(filterPages,"25");           //TODO Check that list<=25
    }

    public void checkFilters() {
        checkFilter(fio, wrestlerFullName1);
        checkFilter(region, wrestler1.regionFirst);
        checkFilter(fst, wrestler1.fstFirst);
        checkFilter(license, wrestler1.license);
        checkFilter(photo, "No");
        checkFilter(style, wrestler1.style);
    }

    public void deleteWrestlers() {
        deleteWrestler(wrestlerFullName1);
        deleteWrestler(wrestlerFullName2);
        deleteWrestler(wrestlerFullName3);
        deleteWrestler(wrestlerFullName4);
        deleteWrestler(wrestlerFullName5);
    }

    //Login block
    @FindBy(xpath = "//div/input [1]")
    public WebElement fieldLogin;

    @FindBy(xpath = "//div/input [@placeholder = 'Password']")
    public WebElement fieldPassword;

    @FindBy(xpath = "//button")
    public WebElement buttonLogin;



    //Profile block
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

    @FindBy(xpath = "//div/button[@ng-click=\"delete()\"]")
    public WebElement deleteWrestler;

    @FindBy(xpath = "//button[@ng-click=\"ok()\"]")
    public WebElement deleteСonfirm;

    @FindBy(xpath = "//li[2]//div//span")
    public WebElement closeProfilePage;

    @FindBy(xpath = "//li[2]//div[1]")           //TODO maybeNotNeedElement
    public WebElement clickFirstPage;



    //Search block
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

    @FindBy(xpath = "//button[2]")
    public WebElement buttonCreateNewWrestler;

    @FindBy(xpath = "///select[@ng-model=\"filters.fregion\"]")
    public WebElement filterRegion;

    @FindBy(xpath = "//select[@ng-model=\"filters.ffst\"]")
    public WebElement filterFST;

    @FindBy(xpath = "//select[@ng-model=\"filters.fyear\"]")
    public WebElement filterLicense;

    @FindBy(xpath = "//select[@ng-model=\"filters.fphoto\"]")
    public WebElement filterPhoto;

    @FindBy(xpath = "//select[@ng-model=\"filters.fstyle\"]")
    public WebElement filterStyle;

    @FindBy(xpath = "//button[@ng-click=\"resetFilters()\"]")
    public WebElement resetFilters;

    @FindBy(xpath = "//button[2]//select[@ng-model=\"perPage\"]")
    public WebElement filterPages;



    //Lists for search result
    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[2]"))
    List<WebElement> number;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[2]"))
    List<WebElement> fio;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[3]"))
    List<WebElement> region;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[4]"))
    List<WebElement> fst;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[5]"))
    List<WebElement> license;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[6]"))
    List<WebElement> photo;

    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[7]"))
    List<WebElement> style;
}
