package lastochkin.streamTV.helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ScreenShot {
    private WebDriver driver;
    private DateFormat dateFormat = new SimpleDateFormat("_MM.dd.yyyy_HH.mm.ss");
    private Date today = Calendar.getInstance().getTime();
    private String sysDate = dateFormat.format(today);

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    public void captureScreen(String fileName){
        File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scr, new File("ScreenShot\\" + fileName + sysDate + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
