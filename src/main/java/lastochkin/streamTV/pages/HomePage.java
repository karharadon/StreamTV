package lastochkin.streamTV.pages;

import lastochkin.streamTV.data.Wrestler;
import lastochkin.streamTV.helper.ConfigProperties;
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

    public Wrestler wrestler1 = new Wrestler("Lana","Rey","12-05-1979","Del","Volynska","Kyivska",
            "Dinamo","SK","Joda","Kenobi","FS","Cadet","2016","Recieved");
    public Wrestler wrestler2 = new Wrestler("Iogan","Mozart","12-05-1989","Amadei","Kyiv","Zaporizka",
            "Kolos","MON","ObiVan","DartaMol","GR","Senior","2017","Produced");
    public Wrestler wrestler3 = new Wrestler("Nautilius","Pompilius","12-05-1989","Karavana","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");
    public Wrestler wrestler4 = new Wrestler("Zigmund","Freid","12-05-1989","Petrovich","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");
    public Wrestler wrestler5 =new Wrestler("Jaroslav","Mudryj","12-05-1989","Knjaz","Kyiv","Zaporizka",
            "Kolos","SK","ObiVan","DartaMol","FS","Senior","2017","Produced");

    public String wrestlerFullName1 = wrestler1.lastName + " " + wrestler1.firstName + " " + wrestler1.middleName;
    public String wrestlerFullName2 = wrestler2.lastName + " " + wrestler2.firstName + " " + wrestler2.middleName;
    public String wrestlerFullName3 = wrestler3.lastName + " " + wrestler3.firstName + " " + wrestler3.middleName;
    public String wrestlerFullName4 = wrestler4.lastName + " " + wrestler4.firstName + " " + wrestler4.middleName;
    public String wrestlerFullName5 = wrestler5.lastName + " " + wrestler5.firstName + " " + wrestler5.middleName;

    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private Date today = Calendar.getInstance().getTime();
    private String sysDate = dateFormat.format(today);

    public ArrayList<String> errorsAfterCreating = new ArrayList<>();
    public ArrayList<String> errorsAfterUpdating = new ArrayList<>();

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

    public void createWrestler(Wrestler wrestler){
        buttonCreateNewWrestler.click();
        fillAllFields(wrestler);
        buttonSave.click();
        waitWhenClickableAndClick(closeProfilePage,7);
    }

    public void fillAllFields(Wrestler wrestler) {
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

    public void findWrestler(String wrestlerFullName) {  //TODO choose last created wrestler, check deletion with last
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
    }

    public void verifySearchResultWithCode(Wrestler wrestler, String wrestlerFullName, ArrayList errors) { //TODO Fail. Element not found.Make try catch
        assertSearchResultWithCode(wrestlerFIO, wrestlerFullName, errors);
        assertSearchResultWithCode(wrestlerRegion, wrestler.regionFirst, errors);
        assertSearchResultWithCode(wrestlerFST, wrestler.fstFirst, errors);
        assertSearchResultWithCode(wrestlerLicense, wrestler.license, errors);
        assertSearchResultWithCode(wrestlerPhoto, "No", errors);
        assertSearchResultWithCode(wrestlerStyle, wrestler.style, errors);
        assertSearchResultWithCode(wrestlerChanged, sysDate, errors);
    }

    public void verifyProfileDataWithCode(Wrestler wrestler, ArrayList errors){
        wrestlerFIO.click();
        assertProfileDataWithCode2(fieldLastName, wrestler.lastName, errors);
        assertProfileDataWithCode2(fieldFirstName, wrestler.firstName, errors);
        assertProfileDataWithCode2(fieldDateOfBirth, wrestler.dateOfBirth, errors);
        assertProfileDataWithCode2(fieldMiddleName, wrestler.middleName, errors);
        assertProfileDataWithCode(fieldRegion1, wrestler.regionFirst, errors);
        assertProfileDataWithCode(fieldRegion2, wrestler.regionSecond, errors);
        assertProfileDataWithCode(fieldFST1, wrestler.fstFirst, errors);
        assertProfileDataWithCode(fieldFST2, wrestler.fstSecond, errors);
        assertProfileDataWithCode2(fieldTrainer1, wrestler.trainerFirst, errors);
        assertProfileDataWithCode2(fieldTrainer2, wrestler.trainerSecond, errors);
        assertProfileDataWithCode(fieldStyle, wrestler.style, errors);
        assertProfileDataWithCode(fieldAge, wrestler.age, errors);
        assertProfileDataWithCode(fieldYear, wrestler.license, errors);
        assertProfileDataWithCode(fieldCard, wrestler.card, errors);
        waitWhenClickableAndClick(closeProfilePage,7);
    }

    public void checkExeptions(ArrayList errors){
        if (errors.size() > 0)
            errors.forEach(System.out::println);
            throw new RuntimeException("Some fields contain wrong data!");
        }

    public void updateWrestler() {
        findWrestler(wrestlerFullName1);
        wrestlerFIO.click();
        fillAllFields(wrestler2);
        buttonSave.click();
        waitWhenClickableAndClick(closeProfilePage,7);
    }

    public void deleteWrestler(String wrestlerFullName) {
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);// TODO org.openqa.selenium.InvalidElementStateException:
        // invalid element state: Element is not currently interactable and may not be manipulated
        buttonSearchFor.click();
        wrestlerFIO.click();
        deleteWrestler.click();
        deleteСonfirm.click();
    }

    public void checkDeletion(String wrestlerFullName) {
        if (checkIfExist(wrestlerFullName)) {
            throw new RuntimeException("Wrestler wasn't deleted or exist wrestlers with a same name!");
        }
    }

    public void deleteAllMyWrestlers(){
        deleteWrestlerWithThisName(wrestlerFullName1);
        deleteWrestlerWithThisName(wrestlerFullName2);
        deleteWrestlerWithThisName(wrestlerFullName3);
        deleteWrestlerWithThisName(wrestlerFullName4);
        deleteWrestlerWithThisName(wrestlerFullName5);
    }

    public void deleteWrestlerWithThisName(String wrestlerFullName) {
        if (checkIfExist(wrestlerFullName)) {
            deleteWrestler(wrestlerFullName);
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

    public void useAndCheckDifferentFilters() {
        filterCombination1();
        checkfilterCombination1();
        filterCombination2();
        checkfilterCombination2();
        filterCombination3();
        checkfilterCombination3();
    }

    private void filterCombination1() {

    }

    private void checkfilterCombination1() {

    }

    private void filterCombination2() {

    }

    private void checkfilterCombination2() {

    }

    private void filterCombination3() {

    }

    private void checkfilterCombination3() {
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
