package lastochkin.streamTV;

import lastochkin.streamTV.helper.ConfigProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import lastochkin.streamTV.pages.HomePage;


import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    public static WebDriver driver;

    protected String browser = ConfigProperties.getProperty("browser");
    HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);


    public WebDriver getWebDriver() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        }
        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        }
        if (browser.equals("internetExplorer")) {
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigProperties.getProperty("impWait")), TimeUnit.SECONDS);
        return driver;
    }

   //@AfterMethod
    public void deleteWrestlersAndTearDown() {
       homePage.deleteAllMyWrestlers();
       driver.close();
    }
}
