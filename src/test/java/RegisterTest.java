
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.HomePage;

public class RegisterTest extends BaseTest {

    HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

    @Test
    public void CRUD(){
        homePage.login(driver);
        homePage.wrestlerCRUD();
    }

    @Test
    public void Filters(){
        homePage.login(driver);
        homePage.createFewWrestlers();
        //homePage.useFilters();
        //homePage.checkFilters();
        homePage.deleteWrestlers();
    }
}
