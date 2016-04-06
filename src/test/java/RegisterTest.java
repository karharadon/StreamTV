
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pages.HomePage;

public class RegisterTest extends BaseTest {

    HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

    @Test
    public void CRUD(){
        homePage.login(driver);
        homePage.wrestlerCRUD();
        //homePage.exeptions();
    }

    @Test
    public void Filters(){
        homePage.login(driver);
        homePage.createFewWrestlersForTestingFilters();
        homePage.useFilters();
        homePage.checkAllFilters();
        homePage.deleteWrestlersCreatedForTestingFilters();
    }

    @Test
    public void deleteAllMyWrestlers(){
        homePage.login(driver);
        homePage.deleteAllMyWrestlers();
    }
  }
