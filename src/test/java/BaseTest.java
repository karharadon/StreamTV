
import data.WrestlerData;
import helper.ConfigProperties;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    public WrestlerData wrestler = new WrestlerData("Linkoln", "Alex", "12-05-1989", "Lion",
            "Kyivska", "Odeska", "Dinamo", "SK",
           "Gashkek", "Scorpion", "FS", "Senior", "2017", "Recieved");

    public static WebDriver driver;

    protected String browser = ConfigProperties.getProperty("browser");


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

   @After
    public void tearDown() {
    //driver.close();
    }


}
