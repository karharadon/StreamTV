package lastochkin.streamTV.pages;

import lastochkin.streamTV.data.Wrestler;
import lastochkin.streamTV.helper.ConfigProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static lastochkin.streamTV.helper.ConfigProperties.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomePage extends AbstractPage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public Wrestler wrestler1 = new Wrestler("Lapa", "Wapa", "12-05-1979", "Dellla", "Volynska", "Kyivska",
            "Dinamo", "SK", "Joda", "Kenobi", "FS", "Cadet", "2016", "Recieved");
    public Wrestler wrestler2 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Kyiv", "Zaporizka",
            "Kolos", "MON", "ObiVan", "DartaMol", "GR", "Senior", "2017", "Produced");
    public Wrestler wrestler3 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Volynska", "Zaporizka",
            "Kolos", "MON", "ObiVan", "DartaMol", "GR", "Senior", "2017", "Produced");
    public Wrestler wrestler4 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Kyiv", "Zaporizka",
            "Dinamo", "MON", "ObiVan", "DartaMol", "GR", "Senior", "2017", "Produced");
    public Wrestler wrestler5 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Kyiv", "Zaporizka",
            "Kolos", "MON", "ObiVan", "DartaMol", "GR", "Senior", "2016", "Produced");
    public Wrestler wrestler6 = new Wrestler("Superman", "Batman", "12-05-1989", "Joker", "Kyiv", "Zaporizka",
            "Kolos", "MON", "ObiVan", "DartaMol", "FW", "Senior", "2017", "Produced");

    public final String wrestlerFIO1 = wrestler1.lastName + " " + wrestler1.firstName + " " + wrestler1.middleName;
    public final String wrestlerFIO2 = wrestler2.lastName + " " + wrestler2.firstName + " " + wrestler2.middleName;

    private final String photoUploaded = "No";
    private final int waitWebElem = Integer.parseInt(getProperty("waitWebElem"));

    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final Date today = Calendar.getInstance().getTime();
    private final String sysDate = dateFormat.format(today);


    public ArrayList<String> errorsAfterCreating = new ArrayList<>();
    public ArrayList<String> errorsAfterUpdating = new ArrayList<>();

    public void login(WebDriver driver) {
        open(driver);
        clearAndSendKeys(fieldLogin, ConfigProperties.getProperty("username"));
        clearAndSendKeys(fieldPassword, ConfigProperties.getProperty("password"));
        buttonLogin.click();
        System.out.println("Site was opened.");
    }

    public void open(WebDriver driver) {
        driver.get(getProperty("url"));
        waitForPageLoad(driver);
    }

    public void createWrestler(Wrestler wrestler) {
        buttonCreateNewWrestler.click();
        fillAllFields(wrestler);
        buttonSave.click();
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
        System.out.println("Wrestler was created.");
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
        System.out.println("All fields was filled.");
    }

    public void findWrestler(String wrestlerFullName) {
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        waitWhenClickableAndClick(buttonSearchFor, waitWebElem);
    }

    public void verifySearchResultWithCode(Wrestler wrestler, String wrestlerFullName, ArrayList<String> errors) {
        assertSearchResulstWithCode(wrestlerFIO, wrestlerFullName, errors);
        assertSearchResulstWithCode(wrestlerRegion, wrestler.regionFirst, errors);
        assertSearchResulstWithCode(wrestlerFST, wrestler.fstFirst, errors);
        assertSearchResulstWithCode(wrestlerLicense, wrestler.license, errors);
        assertSearchResulstWithCode(wrestlerPhoto, photoUploaded, errors);
        assertSearchResulstWithCode(wrestlerStyle, wrestler.style, errors);
        assertSearchResulstWithCode(wrestlerChanged, sysDate, errors);
        System.out.println("Search results were verified with code.");
    }

    public void verifyProfileDataWithCode(Wrestler wrestler, ArrayList<String> errors) {
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
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
        System.out.println("Profile data were verified with code.");
    }

    public void checkExeptions(ArrayList<String> errors) {
        if (errors.size() > 0)
            errors.forEach(System.out::println);
        throw new RuntimeException("Some fields contain wrong data!");
    }

    public void updateWrestler() {
        findWrestler(wrestlerFIO1);
        waitWhenClickableAndClick(wrestlerFIO, waitWebElem);
        fillAllFields(wrestler2);
        buttonSave.click();
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
        System.out.println("Wrestler was updated");
    }

    public void deleteWrestler(String wrestlerFullName) {
        try {
            clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        } catch (InvalidElementStateException e) {
            waitWhenClickableAndClick(fieldSearchFor, waitWebElem);
            clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        }

        waitWhenClickableAndClick(buttonSearchFor, waitWebElem);
        waitWhenClickableAndClick(wrestlerFIO, waitWebElem);
        deleteWrestler.click();
        deleteConfirm.click();
        System.out.println("Wrestler was deleted.");
    }

    public void checkDeletion(String wrestlerFullName) {
        if (isExist(wrestlerFullName)) {
            throw new RuntimeException("Wrestler wasn't deleted or exist wrestlers with a same name!");
        }
    }

    public void deleteWrestlerWithThisName(String wrestlerFullName) {
        if (isExist(wrestlerFullName)) {
            for (int i = 0; i < fio.size(); i++) {
                deleteWrestler(wrestlerFullName);
            }
        }
    }

    private boolean isExist(String wrestlerFullName) {
        waitWhenClickableAndClick(fieldSearchFor, waitWebElem);
        clearAndSendKeys(fieldSearchFor, wrestlerFullName);
        waitWhenClickableAndClick(buttonSearchFor, waitWebElem);
        return fio.size() != 0;
    }

    public void createFewWrestlersForTestingFilters() {
        createWrestler(wrestler2);
        createWrestler(wrestler3);
        createWrestler(wrestler4);
        createWrestler(wrestler5);
        createWrestler(wrestler6);
        System.out.println("Wrestlers for filters were made");
    }


    public void useAndCheckDifferentFilters() {
        String filterPerPage = "50";
        String exceptionText = "Amount of wrestlers after filter != expected";

        waitWhenClickableAndClick(buttonSearchFor, waitWebElem);
        selectFromDD(filterPages, filterPerPage);
        assertThat("Amount of wrestlers on the page more than filter \"perPage\"!",
                number.size() <= Integer.parseInt(filterPerPage));

        clearAndSendKeys(fieldSearchFor, wrestlerFIO2);
        waitWhenClickableAndClick(buttonSearchFor, waitWebElem);
        if (fio.size() != 5) throw new RuntimeException(exceptionText);
        checkFilter(fio, wrestlerFIO2);

        selectFromDD(filterRegion, wrestler2.regionFirst);
        if (fio.size() != 4) throw new RuntimeException(exceptionText);
        checkFilter(region, wrestler2.regionFirst);

        selectFromDD(filterFST, wrestler2.fstFirst);
        if (fio.size() != 3) throw new RuntimeException(exceptionText);
        checkFilter(fst, wrestler2.fstFirst);

        selectFromDD(filterLicense, wrestler2.license);
        if (fio.size() != 2) throw new RuntimeException(exceptionText);
        checkFilter(license, wrestler2.license);

        selectFromDD(filterPhoto, photoUploaded);
        checkFilter(photo, photoUploaded);

        selectFromDD(filterStyle, wrestler2.style);
        if (fio.size() != 1) throw new RuntimeException(exceptionText);
        checkFilter(style, wrestler2.style);

        resetFilters();
        if (fio.size() != 5) throw new RuntimeException(exceptionText);
    }

    private void resetFilters() {
        try {
            resetFilters.click();
        } catch (WebDriverException e) {
            ((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
                    + "evt.initMouseEvent('click',true, " + "true, " + "window, 0, 0, 0, 0, 0, false, false, false," +
                    " false, 0,null);" + "arguments[0].dispatchEvent(evt);", resetFilters);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot click on button or field.");
        }
    }

    public void deleteWrestlersCreatedForTestingFilters() {
        for (int i = 0; i < 5; i++) {
            deleteWrestler(wrestlerFIO2);
        }
        System.out.println("All wrestlers created for filters were deleted");
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
    WebElement fieldLastName;

    @FindBy(xpath = "//div/input[@placeholder='First name']")
    WebElement fieldFirstName;

    @FindBy(xpath = "//div/input[@placeholder='Date of Birth']")
    WebElement fieldDateOfBirth;

    @FindBy(xpath = "//div/input[@placeholder='Middle name']")
    WebElement fieldMiddleName;

    @FindBy(xpath = "//fg-select [@value=\"wr.region1\"]//select")
    WebElement fieldRegion1;

    @FindBy(xpath = "//fg-select [@value=\"wr.region2\"]//select")
    WebElement fieldRegion2;

    @FindBy(xpath = "//fg-select [@value=\"wr.fst1\"]//select")
    WebElement fieldFST1;

    @FindBy(xpath = "//fg-select [@value=\"wr.fst2\"]//select")
    WebElement fieldFST2;

    @FindBy(xpath = "//fg-typeahead[@value= \"wr.trainer1\"]//input")
    WebElement fieldTrainer1;

    @FindBy(xpath = "//fg-typeahead [@value=\"wr.trainer2\"]//input")
    WebElement fieldTrainer2;

    @FindBy(xpath = "//fg-select [@value=\"wr.style\"]//select")
    WebElement fieldStyle;

    @FindBy(xpath = "//fg-select [@value=\"wr.lictype\"]//select")
    WebElement fieldAge;

    @FindBy(xpath = "//fg-select [@value=\"wr.expires\"]//select")
    WebElement fieldYear;

    @FindBy(xpath = "//f-select [@value=\"wr.card_state\"]//select")
    WebElement fieldCard;

    @FindBy(xpath = "//div[@class=\"button\"][1]/button")
    WebElement buttonSave;

    @FindBy(xpath = "//div/button[@ng-click=\"delete()\"]")
    WebElement deleteWrestler;

    @FindBy(xpath = "//button[@ng-click=\"ok()\"]")
    WebElement deleteConfirm;

    @FindBy(xpath = "//li[2]//div//span")
    WebElement closeProfilePage;


    @FindBy(xpath = "//input[@uploader=\"photoUploader\"]")
    public
    WebElement formForPhoto;

    @FindBy(xpath = "//input[@uploader=\"attachUploader\"]")
    public
    WebElement formForAttachment;

    @FindBy(xpath = "//img[@class=\"center-block\"]")
    public
    WebElement image;

    @FindBy(xpath = "//td/a[@class=\"ng-binding\"]")
    public
    WebElement uploadedAttachment;

    @FindBy(xpath = "//ico[@ng-click=\"deleteAttach($index)\"]")
    public
    WebElement deleteAttachment;


    //Search block
    @FindBy(xpath = "//input[@ng-model=\"searchFor\"]")
    WebElement fieldSearchFor;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement buttonSearchFor;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public
    WebElement wrestlerFIO;

    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    WebElement wrestlerRegion;

    @FindBy(xpath = "//tbody/tr[1]/td[4]")
    WebElement wrestlerFST;

    @FindBy(xpath = "//tbody/tr[1]/td[5]")
    WebElement wrestlerLicense;

    @FindBy(xpath = "//tbody/tr[1]/td[6]")
    WebElement wrestlerPhoto;

    @FindBy(xpath = "//tbody/tr[1]/td[7]")
    WebElement wrestlerStyle;

    @FindBy(xpath = "//tbody/tr[1]/td[8]")
    WebElement wrestlerChanged;

    @FindBy(xpath = "//button[2]")
    WebElement buttonCreateNewWrestler;

    @FindBy(xpath = "//select[@ng-model=\"filters.fregion\"]")
    WebElement filterRegion;

    @FindBy(xpath = "//select[@ng-model=\"filters.ffst\"]")
    WebElement filterFST;

    @FindBy(xpath = "//select[@ng-model=\"filters.fyear\"]")
    WebElement filterLicense;

    @FindBy(xpath = "//select[@ng-model=\"filters.fphoto\"]")
    WebElement filterPhoto;

    @FindBy(xpath = "//select[@ng-model=\"filters.fstyle\"]")
    WebElement filterStyle;

    @FindBy(xpath = "//button[@ng-click=\"resetFilters()\"]")
    WebElement resetFilters;

    @FindBy(xpath = "//select[@ng-model=\"perPage\"]")
    WebElement filterPages;


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


    public boolean imageIsEqual(String expectedFile, String actualFile) throws IOException {

        BufferedImage bufImExpected = ImageIO.read(new File(expectedFile));
        DataBuffer datBufExpected = bufImExpected.getData().getDataBuffer();
        int sizefileExp = datBufExpected.getSize();

        BufferedImage bufImActual = ImageIO.read(new File(actualFile));
        DataBuffer datBufActual = bufImActual.getData().getDataBuffer();
        int sizeFileActual = datBufActual.getSize();

        Boolean isEqual = true;
        if (sizefileExp == sizeFileActual) {
            for (int j = 0; j < sizefileExp; j++) {
                if (datBufExpected.getElem(j) != datBufActual.getElem(j)) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }
        return isEqual;

    }

    File downloadedImage = null;
    String expectedImage = System.getProperty("user.dir") + "/src/main/resources/expectedImage.png";
    String imageForUploading = System.getProperty("user.dir") + "/src/main/resources/imageForUploading.jpg";
    String attachmentForUploading = System.getProperty("user.dir") + "/src/main/resources/attachmentForTest.txt";
    String attachmentName = "attachmentForTest.txt";

    public String downloadPhoto() throws IOException {
        findWrestler(wrestlerFIO1);
        waitWhenClickableAndClick(wrestlerFIO, waitWebElem);
        String location = image.getAttribute("src");
        URL url = new URL(location);
        BufferedImage bufImgOne = ImageIO.read(url);
        downloadedImage = File.createTempFile("downloadedPhoto", ".png");
        ImageIO.write(bufImgOne, "png", downloadedImage);
        return downloadedImage.getAbsolutePath();
    }

    public void uploadImage() {
        waitWhenClickableAndClick(wrestlerFIO, waitWebElem);
        formForPhoto.sendKeys(imageForUploading);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
    }

    public void checkThatCorrectImageWasUploaded() throws IOException {
        boolean arePhotosEqual = imageIsEqual(expectedImage, downloadPhoto());
        assertThat("Downloaded image is not as expected", arePhotosEqual, is(true));
        System.out.println("Correct image was uploaded");
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
        downloadedImage.deleteOnExit();
    }

    public void uploadAttachment() {
        waitWhenClickableAndClick(wrestlerFIO, waitWebElem);
        formForAttachment.sendKeys(attachmentForUploading);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkThatCorrectAttachmentWasUploaded() {
        assertThat("Uploaded file has unexpected name!", attachmentName.equals(uploadedAttachment.getText()));
        System.out.println("Correct file was uploaded.");
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
    }

    public void checkAttachmentDeletation() {
        findWrestler(wrestlerFIO1);
        waitWhenClickableAndClick(wrestlerFIO, waitWebElem);
        waitWhenClickableAndClick(deleteAttachment, waitWebElem);
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
        findWrestler(wrestlerFIO1);
        waitWhenClickableAndClick(wrestlerFIO, waitWebElem);
        assertThat("Attachment file was not deleted!", NumberOfAttachments() == 0);
        System.out.println("Attachment file was deleted successfully");
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
    }

    public int NumberOfAttachments() {
        return driver.findElements(By.xpath("//div[@class='file-drop']/table/tbody/tr")).size();
    }

}
