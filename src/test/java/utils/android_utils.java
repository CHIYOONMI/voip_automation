package utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class android_utils {

    static WebElement element;

    static public String getTime(){
        Date today = new Date();
        SimpleDateFormat ampm = new SimpleDateFormat("a", Locale.JAPAN);
        SimpleDateFormat time = new SimpleDateFormat("h:mm");

        String first = ampm.format(today);
        String second = time.format(today);

        return first + second;
    }

    static public String getScreenshot(WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
        File target = new File(dest);
        FileUtils.copyFile(src, target);

        return dest;
    }

    static public String getHeaderTitle(AndroidDriver driver){
        String headerTitle;
        headerTitle = driver.findElement(By.id("jp.naver.line.android:id/header_title")).getText();
        return headerTitle;
    }

    static public WebElement findString(AndroidDriver driver, String targetString){
        element = driver.findElementByAndroidUIAutomator("new UiSelector().text(" + targetString + ")");
        return element;
    }

}
