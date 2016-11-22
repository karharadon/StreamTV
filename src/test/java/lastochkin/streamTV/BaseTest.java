package lastochkin.streamTV;

import lastochkin.streamTV.helper.ConfigProperties;
import lastochkin.streamTV.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    public static WebDriver driver;

    private final String browser = System.getProperty("browser");
    HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

    private WebDriver getWebDriver() {
        if (driver == null) {
            if (browser.equals("chrome")) {
                driver = new ChromeDriver();
            }
            if (browser.equals("firefox")) {
                driver = new FirefoxDriver();
            }
            if (browser.equals("internetExplorer")) {
                driver = new InternetExplorerDriver();
            }

            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("impWait")),
                        TimeUnit.SECONDS);
                driver.manage().window().maximize();
            }
        }
        return driver;
    }

    @BeforeClass(enabled = false)
    public void beforeClass() {
        System.out.println(".............BEFORE CLASS................");
        homePage.login(driver);
        homePage.deleteWrestlerWithThisName(homePage.wrestlerFIO1);
        homePage.deleteWrestlerWithThisName(homePage.wrestlerFIO2);
        System.out.println("There are no wrestlers. Test start.");
        System.out.println(".........................................");
    }

    @AfterMethod(enabled = false)
    public void afterMethod() {
        driver.close();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("..............TEST START................");
    }

    @AfterSuite(enabled = false)
    public void closeBrowser() {
        if(driver!=null){
            driver.close();
        }
        System.out.println("Browser closed.");
        System.out.println(".............TEST SUIT IS FINISHED.............");
    }
}
