import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

public class RegisterTest extends BaseTest {

    HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);


    @Test
    public void Register(){
        homePage.login(driver);
        homePage.wrestlerCRUD();
    }
}
