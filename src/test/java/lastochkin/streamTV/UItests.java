package lastochkin.streamTV;

import org.testng.annotations.*;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UItests extends BaseTest {

    @Override
    @AfterMethod(enabled = true)
    public void afterMethod() {
        System.out.println("...................AFTER METHOD......................");
        homePage.deleteWrestlerWithThisName(homePage.wrestlerFIO1);
        homePage.deleteWrestlerWithThisName(homePage.wrestlerFIO2);
        System.out.println("Wrestlers created for tests don't exist.");
        System.out.println(".....................................................");
    }

    @Test(priority = 1)
    public void createAndDelete() {
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.findWrestler(homePage.wrestlerFIO1);
        homePage.deleteWrestler(homePage.wrestlerFIO1);
        homePage.checkDeletion(homePage.wrestlerFIO1);
    }

    @Test(priority = 2)
    public void createAndVerify() {
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.findWrestler(homePage.wrestlerFIO1);
        homePage.verifySearchResultWithCode(homePage.wrestler1, homePage.wrestlerFIO1,
                homePage.errorsAfterCreating);
        homePage.verifyProfileDataWithCode(homePage.wrestler1, homePage.errorsAfterCreating);
        homePage.deleteWrestler(homePage.wrestlerFIO1);
        homePage.checkExeptions(homePage.errorsAfterCreating);
    }

    @Test(priority = 3)
    public void updateAndVerify() {
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.updateWrestler();
        homePage.findWrestler(homePage.wrestlerFIO2);
        homePage.verifySearchResultWithCode(homePage.wrestler2, homePage.wrestlerFIO2,
                homePage.errorsAfterUpdating);
        homePage.verifyProfileDataWithCode(homePage.wrestler2, homePage.errorsAfterUpdating);
        homePage.deleteWrestler(homePage.wrestlerFIO2);
        homePage.checkExeptions(homePage.errorsAfterUpdating);
    }

    @Test(priority = 4)
    public void filters() {
        homePage.login(driver);
        homePage.createFewWrestlersForTestingFilters();
        homePage.useAndCheckDifferentFilters();
        homePage.deleteWrestlersCreatedForTestingFilters();
    }

    @Test(priority = 5)
    public void uploadImage() throws IOException {
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.findWrestler(homePage.wrestlerFIO1);
        homePage.uploadImage();
        homePage.checkThatCorrectImageWasUploaded();
        homePage.deleteWrestler(homePage.wrestlerFIO1);
    }

    @Test(priority = 6)
    public void uploadAndDeleteAttachment() throws IOException {
        homePage.login(driver);
        homePage.createWrestler(homePage.wrestler1);
        homePage.findWrestler(homePage.wrestlerFIO1);
        homePage.uploadAttachment();
        homePage.checkThatCorrectAttachmentWasUploaded();
        homePage.checkAttachmentDeletation();
        homePage.deleteWrestler(homePage.wrestlerFIO1);
    }
}


