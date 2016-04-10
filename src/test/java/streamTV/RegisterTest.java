package streamTV;

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
        homePage.verifySearchResultWithCode(homePage.wrestler1,homePage.wrestlerFullName1,homePage.errorsAfterCreating);
        homePage.verifyProfileDataWithCode(homePage.wrestler1, homePage.errorsAfterCreating);
        homePage.deleteWrestler(homePage.wrestlerFullName1);
        homePage.checkExeptions(homePage.errorsAfterCreating);
    }

    @Test
    public void updateAndVerify(){
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.updateWrestler();
        homePage.findWrestler(homePage.wrestlerFullName2);
        homePage.verifySearchResultWithCode(homePage.wrestler2,homePage.wrestlerFullName2,homePage.errorsAfterUpdating);
        homePage.verifyProfileDataWithCode(homePage.wrestler2,homePage.errorsAfterUpdating);
        homePage.deleteWrestler(homePage.wrestlerFullName2);
        homePage.checkExeptions(homePage.errorsAfterUpdating);
    }

    @Test
    public void Filters(){
        homePage.login(driver);
        homePage.createFewWrestlersForTestingFilters();
        homePage.useAndCheckDifferentFilters();
        homePage.deleteWrestlersCreatedForTestingFilters();
    }


    //@Test
    public void Filtersssss(){
        homePage.login(driver);
        homePage.createFewWrestlersForTestingFilters();
        homePage.useFilters();
        homePage.checkAllFilters();
        homePage.deleteWrestlersCreatedForTestingFilters();
    }
}
