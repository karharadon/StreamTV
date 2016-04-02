package pages;

import data.WrestlerData;
import helper.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomePage extends AbstractPage {

    WrestlerData wrestlerData = new WrestlerData("Ukraina","Francuana","12-05-1989","Maroccana","Volynska","Kyiv",
            "Dinamo",
            "Kolos","Joda","Palladin","FW","Cadet","2016","Recieved");

    WrestlerData wrestlerDataforUpdate = new WrestlerData("Britana","Indiana","12-05-1979","Kanadiana","Kyiv",
            "Zaporizka","Kolos",
            "SK","ObiVan","DartaMol","FS","Senior","2017","Produced");

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

    public void createWrestler() {
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

        //TODO write method without dublicate
        waitWhenClickable(closeFirstPage,7);
        closeFirstPage.click();
    }

    private void findWrestler() {
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
    }

    private void verifySearchResultWithCodeData() {
        assertThat("The field \"FIO\" expected contains " + wrestlerFullName + " but was: " + wrestlerFIO.getText(),
                wrestlerFIO.getText().equals(wrestlerFullName));

        assertThat("The field \"Region\" expected contains " + wrestlerData.regionFirst + " but was: " +
                wrestlerRegion.getText(),wrestlerRegion.getText().equals(wrestlerData.regionFirst));

        assertThat("The field \"FST\" expected contains " + wrestlerData.fstFirst + " but was: " +
                wrestlerFST.getText(),wrestlerFST.getText().equals(wrestlerData.fstFirst));

        assertThat("The field \"License\" expected contains " + wrestlerData.year + " but was: " +
                wrestlerLicense.getText(),wrestlerLicense.getText().equals(wrestlerData.year));

        assertThat("The field \"License\" expected contains " + wrestlerData.year + " but was: " +
                wrestlerLicense.getText(),wrestlerLicense.getText().equals(wrestlerData.year));

        assertThat("The field \"Photo\" expected contains \"No\" but was: Yes" ,
                wrestlerPhoto.getText().equals("No"));

        assertThat("The field \"Style\" expected contains " + wrestlerData.style + " but was: " +
                wrestlerStyle.getText(),wrestlerStyle.getText().equals(wrestlerData.style));

        assertThat("The field \"Changed\" expected contains " + sysDate + " but was: " +
                wrestlerChanged.getText(),wrestlerChanged.getText().equals(sysDate));
        }

    //TODO make list with failed methods and throw exeption
    private void verifyProfileDataWithCode(){
        wrestlerFIO.click();

        assertThat("The field \"Last Name\" expected contains " + wrestlerData.lastName + " but was: "
                + fieldLastName.getAttribute("value"),
                fieldLastName.getAttribute("value").equals(wrestlerData.lastName));

        assertThat("The field \"First Name\" expected contains " + wrestlerData.firstName + " but was: "
                + fieldFirstName.getAttribute("value"),
                fieldFirstName.getAttribute("value").equals(wrestlerData.firstName));

        assertThat("The field \"Date of Birth\" expected contains " + wrestlerData.dateOfBirth + " but was: "
                + fieldDateOfBirth.getAttribute("value"),
                fieldDateOfBirth.getAttribute("value").equals(wrestlerData.dateOfBirth));

        assertThat("The field \"Middle name\" expected contains " + wrestlerData.middleName + " but was: "
                + fieldMiddleName.getAttribute("value"),
                fieldMiddleName.getAttribute("value").equals(wrestlerData.middleName));

        assertThat("The field \"Region(1)\" expected contains " + wrestlerData.regionFirst + " but was: "
                + getComboboxElement(fieldRegion1),
                getComboboxElement(fieldRegion1).equals(wrestlerData.regionFirst));

       /* assertThat("The field \"Region(2)\" expected contains " + wrestlerData.regionSecond + " but was: "
                + c(fieldRegion2),
                getComboboxElement(fieldRegion2).equals(wrestlerData.regionSecond));*/

        assertThat("The field \"FST(1)\" expected contains " + wrestlerData.fstFirst + " but was: "
                + getComboboxElement(fieldFST1),
                getComboboxElement(fieldFST1).equals(wrestlerData.fstFirst));

        /*assertThat("The field \"FST(2)\" expected contains " + wrestlerData.fstSecond + " but was: " +
                getComboboxElement(fieldFST2),
                getComboboxElement(fieldFST2).equals(wrestlerData.fstSecond));

        assertThat("The field \"Trainer(1)\" expected contains " + wrestlerData.trainerFirst + " but was: "
                + getComboboxElement(fieldTrainer1),
                getComboboxElement(fieldTrainer1).equals(wrestlerData.trainerFirst));

        assertThat("The field \"Trainer(2)\" expected contains " + wrestlerData.trainerSecond + " but was: "
                + getComboboxElement(fieldTrainer2),
                getComboboxElement(fieldTrainer2).equals(wrestlerData.trainerSecond));*/

        assertThat("The field \"Style\" expected contains " + wrestlerData.style + " but was: "
                + getComboboxElement(fieldStyle),
                getComboboxElement(fieldStyle).equals(wrestlerData.style));

        assertThat("The field \"Age\" expected contains " + wrestlerData.age + " but was: "
                + getComboboxElement(fieldAge),
                getComboboxElement(fieldAge).equals(wrestlerData.age));

        assertThat("The field \"Year\" expected contains " + wrestlerData.year + " but was: "
                + getComboboxElement(fieldYear),
                getComboboxElement(fieldYear).equals(wrestlerData.year));

        /*assertThat("The field \"Card\" expected contains " + wrestlerData.card + " but was: "
                + getComboboxElement(fieldCard),
                getComboboxElement(fieldCard).equals(wrestlerData.card));*/

        //TODO make 1 method
        waitWhenClickable(closeFirstPage,7);
        closeFirstPage.click();
        }

    private void updateWrestler() {
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        buttonSearchFor.click();
        wrestlerFIO.click();
        clearAndSendKeys(fieldLastName,wrestlerDataforUpdate.lastName);
        clearAndSendKeys(fieldFirstName,wrestlerDataforUpdate.firstName);
        clearAndSendKeys(fieldDateOfBirth,wrestlerDataforUpdate.dateOfBirth);
        clearAndSendKeys(fieldMiddleName,wrestlerDataforUpdate.middleName);
        selectFromDD(fieldRegion1,wrestlerDataforUpdate.regionFirst);
        selectFromDD(fieldRegion2,wrestlerDataforUpdate.regionSecond);
        selectFromDD(fieldFST1,wrestlerDataforUpdate.fstFirst);
        selectFromDD(fieldFST2,wrestlerDataforUpdate.fstSecond);
        clearAndSendKeys(fieldTrainer1,wrestlerDataforUpdate.trainerFirst);
        clearAndSendKeys(fieldTrainer2,wrestlerDataforUpdate.trainerSecond);
        selectFromDD(fieldStyle,wrestlerDataforUpdate.style);
        selectFromDD(fieldAge,wrestlerDataforUpdate.age);
        selectFromDD(fieldYear,wrestlerDataforUpdate.year);
        selectFromDD(fieldCard,wrestlerDataforUpdate.card);
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

        wrestlerPage.click();
        clearAndSendKeys(fieldSearchFor, wrestlerFullUpdateName);
        buttonSearchFor.click();

       //TODO change "null" that method became work
        assertThat("The first row expected contains \"NULL\" but was: " + wrestlerFIO.getText(),
                wrestlerFIO.getText().equals(null));
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
