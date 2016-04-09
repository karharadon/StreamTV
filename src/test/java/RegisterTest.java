
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void createAndDelete(){
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.findWrestler(homePage.wrestlerFullName1);
        homePage.deleteWrestler(homePage.wrestlerFullName1);
        homePage.checkDeletion(homePage.wrestlerFullName1);
    }

    @Test
    public void createAndVerify(){
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.findWrestler(homePage.wrestlerFullName1);
        homePage.verifySearchResultWithCode(homePage.wrestler1, homePage.wrestlerFullName1);
        homePage.verifyProfileDataWithCode(homePage.wrestler1);
        homePage.deleteWrestler(homePage.wrestlerFullName1);
        homePage.checkExeptions();
    }

    @Test
    public void updateAndVerify(){
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.updateWrestler();
        homePage.findWrestler(homePage.wrestlerFullName2);
        homePage.verifySearchResultWithCode(homePage.wrestler2, homePage.wrestlerFullName2);
        homePage.verifyProfileDataWithCode(homePage.wrestler2);
        homePage.deleteWrestler(homePage.wrestlerFullName2);
        homePage.checkExeptions();
    }

    @Test
    public void Filters(){
        homePage.login(driver);
        homePage.createFewWrestlersForTestingFilters();
        homePage.useAndCheckDifferentFilters();
        homePage.deleteWrestlersCreatedForTestingFilters();
    }


    @Test
    public void Filtersssss(){
        homePage.login(driver);
        homePage.createFewWrestlersForTestingFilters();
        homePage.useFilters();
        homePage.checkAllFilters();
        homePage.deleteWrestlersCreatedForTestingFilters();
    }
}
