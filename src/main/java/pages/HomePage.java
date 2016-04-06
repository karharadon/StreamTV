package pages;

import data.WrestlerData;
import helper.ConfigProperties;
import org.openqa.selenium.NoSuchElementException;
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

    public WrestlerData abstractWrestler = new WrestlerData("","","","","","","","","","","","","","");
    public WrestlerData wrestler1 = new WrestlerData("Britana","Indiana","12-05-1979","Kanadiana","Volynska","Kyivska",
            "Dinamo","SK","Joda","Kenobi","FS","Cadet","2016","Recieved");
    public WrestlerData wrestler2 = new WrestlerData("Karolina","Italiana","12-05-1989","Karavana","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");
    public WrestlerData wrestler3 = new WrestlerData("Karolina","Italiana","12-05-1989","Karavana","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");
    public WrestlerData wrestler4 = new WrestlerData("Karolina","Italiana","12-05-1989","Karavana","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");
    public WrestlerData wrestler5 =new WrestlerData("Karolina","Italiana","12-05-1989","Karavana","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");

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
        this.abstractWrestler = wrestler;
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
        assertSearchResultWithCode(wrestlerFIO, wrestlerFullName1, errors);
        assertSearchResultWithCode(wrestlerRegion, wrestler1.regionFirst, errors);
        assertSearchResultWithCode(wrestlerFST, wrestler1.fstFirst, errors);
        assertSearchResultWithCode(wrestlerLicense, wrestler1.license, errors);
        assertSearchResultWithCode(wrestlerPhoto, "No", errors);
        assertSearchResultWithCode(wrestlerStyle, wrestler1.style, errors);
        assertSearchResultWithCode(wrestlerChanged, sysDate, errors);
    }

    private void verifyProfileDataWithCode(){
        wrestlerFIO.click();
        assertProfileDataWithCode2(fieldLastName, wrestler1.lastName, errors);
        assertProfileDataWithCode2(fieldFirstName, wrestler1.firstName, errors);
        assertProfileDataWithCode2(fieldDateOfBirth, wrestler1.dateOfBirth, errors);
        assertProfileDataWithCode2(fieldMiddleName, wrestler1.middleName, errors);
        assertProfileDataWithCode(fieldRegion1, wrestler1.regionFirst, errors);
        assertProfileDataWithCode(fieldRegion2, wrestler1.regionSecond, errors);
        assertProfileDataWithCode(fieldFST1, wrestler1.fstFirst, errors);
        assertProfileDataWithCode(fieldFST2, wrestler1.fstSecond, errors);
        assertProfileDataWithCode2(fieldTrainer1, wrestler1.trainerFirst, errors);
        assertProfileDataWithCode2(fieldTrainer2, wrestler1.trainerSecond, errors);
        assertProfileDataWithCode(fieldStyle, wrestler1.style, errors);
        assertProfileDataWithCode(fieldAge, wrestler1.age, errors);
        assertProfileDataWithCode(fieldYear, wrestler1.license, errors);
        assertProfileDataWithCode(fieldCard, wrestler1.card, errors);
        waitWhenClickableAndClick(closeProfilePage,7);
    }

    public void exeptions(){
        if (errors.size() > 0)
            errors.forEach(System.out::println);
            throw new RuntimeException("Some fields are empty!");
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

    public void deleteWrestler(String wrestlerFullName) {
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
        wrestlerFIO.click();
        deleteWrestler.click();
        deleteСonfirm.click();
        if(checkIfExist(wrestlerFullName)){
            throw new RuntimeException ("Wrestler wasn't deleted!");
        }
    }

    public void deleteAllMyWrestlers(){
        deleteAllWrestlersWithThisName(wrestlerFullName1);
        deleteAllWrestlersWithThisName(wrestlerFullName2);
        deleteAllWrestlersWithThisName(wrestlerFullName3);
        deleteAllWrestlersWithThisName(wrestlerFullName4);
        deleteAllWrestlersWithThisName(wrestlerFullName5);
    }

    public void deleteAllWrestlersWithThisName(String wrestlerFullName) {
        if (checkIfExist(wrestlerFullName)) {
            int size = fio.size();
            for (int i = 0; i <= size - 1; i++) {
                deleteWrestler(wrestlerFullName);
            }
        }
    }

    private boolean checkIfExist(String wrestlerFullName) {
        waitWhenClickableAndClick(fieldSearchFor, 7);
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
        try {
            assertThat("The first row expected contains " + wrestlerFullName + " but was: " + wrestlerFIO.getText(),
                    wrestlerFIO.getText().equals(wrestlerFullName));
            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void createFewWrestlersForTestingFilters() {
        createWrestler(wrestler1);
        createWrestler(wrestler2);
        createWrestler(wrestler3);
        createWrestler(wrestler4);
        createWrestler(wrestler5);
    }

    public void useFilters() {
        clearAndSendKeys(fieldSearchFor,wrestlerFullName2);
        buttonSearchFor.click();
        selectFromDD(filterRegion,wrestler2.regionFirst);
        selectFromDD(filterFST,wrestler2.fstFirst);
        selectFromDD(filterLicense,wrestler2.license);
        selectFromDD(filterPhoto,"No");
        selectFromDD(filterStyle,wrestler2.style);
        selectFromDD(filterPages,"50");
    }

    public void checkAllFilters() {
        checkFilter(fio, wrestlerFullName2);
        checkFilter(region, wrestler2.regionFirst);
        checkFilter(fst, wrestler2.fstFirst);
        checkFilter(license, wrestler2.license);
        checkFilter(photo, "No");
        checkFilter(style, wrestler2.style);
        assertThat("Amount of wrestlers on the page more than filter \"perPage\"!",number.size() <= 50);
        resetFilters.click(); //TODO org.openqa.selenium.WebDriverException: unknown error: Element is not clickable at point (292, 106). Other element would receive the click: <div class="col-sm-12 paging">...</div>
    }

    public void deleteWrestlersCreatedForTestingFilters() {
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

    @FindBy(xpath = "//select[@ng-model=\"filters.fregion\"]")
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

    @FindBy(xpath = "//select[@ng-model=\"perPage\"]")
    public WebElement filterPages;



    //Lists for search result
    @FindAll(@FindBy(how = How.XPATH, using = "//tr/td[1]"))
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
