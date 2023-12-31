package BaseClassPackage;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Utility extends BaseClass{
	
	public static File captureScreenshot(WebDriver driver ,String testName) {
		String screenShotPath = null;
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
       
        try {
            screenShotPath = System.getProperty("user.dir")+"\\Screenshot\\"+testName+"_screenshot.png";
            FileUtils.copyFile(src, new File(screenShotPath));
          
        } catch (Exception e) {
            e.printStackTrace();
        }
		return src;
    }
}
