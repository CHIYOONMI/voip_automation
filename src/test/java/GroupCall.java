package utils
import io.appium.java_client.touch.ActionOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GroupCall {

    public AndroidDriver driver = null;
    public DesiredCapabilities capabilities = new DesiredCapabilities();
    public WebElement element;
    public String prefix = "jp.naver.line.android";
    public String p1 = "(//android.view.ViewGroup[@content-desc=";
    public String join_device = "galaxy s6";
    public String start_device = "Galaxy S10";
    public String room_name = "auto";
    public String call_type = "Video call";
    DeviceControl DeviceControl = new DeviceControl();

    @BeforeClass(alwaysRun = true)
    @Parameters({"platform", "port", "udid", "device", "ver", "autoname", "systemp"})
    public void setUp(/* String ip, */ String platform, String port, String udid, String device, String ver, String autoname, String systemp) throws MalformedURLException, InterruptedException {

        if (platform == "window") {
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("systemPort", systemp);
        } else {
            capabilities.setCapability("newCommandTimeout", 10000); // appium timeout
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("platformVersion", ver);
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("appPackage", "jp.naver.line.android");
            capabilities.setCapability("deviceName", device);// Galaxy Note5
            capabilities.setCapability("udid", udid);
            capabilities.setCapability("appActivity", ".activity.SplashActivity");
            capabilities.setCapability("unicodeKeyboard", true);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("automationName", autoname);
            capabilities.setCapability("systemPort", systemp);

            driver = new AndroidDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // findElement timeout

            // Todo Call API class
        }
    }

    // Start GC
    @Test
    public void GC_TC_01 () throws InterruptedException {

        element = driver.findElementByXPath(p1 + "\"@{bottomNavigationBarButtonViewModel.contentDescription\"])[4]/android.widget.TextView");
        String text1 = element.getText();
        Assert.assertEquals(text1, "Calls");

        element = driver.findElementByXPath(p1 + "\"@{bottomNavigationBarButtonViewModel.contentDescription\"])[4]/android.view.View");
        element.click();
        /*
        element = driver.findElementByXPath("//android.widget.TextView[@text='" + room_name + "']");
        element.click();

        element = driver.findElementByXPath("//android.widget.TextView[@text='" + call_type + "']");
        element.click();

        // avoiding audio feedback - speaker off
        element = driver.findElementByXPath(p1 + "\"Turn off microphone\"]");
        element.click();
        */
        DeviceControl.selectRoomName(driver, "auto");
        DeviceControl.selectCallType(driver, "Video call");
        DeviceControl.clickTrunOffMicBtn(driver);
    }


    // start device
    @Test
    public void GC_TC_02 () throws InterruptedException {
        // start device
        while (1==1) {
            if (driver.findElementByXPath("//android.widget.TextView[@text='"+join_device+"']").isDisplayed())
                Thread.sleep(10000);
            TouchAction ta = new TouchAction(driver);
            ta.press(PointOption.point(274, 2124)).release().perform();

            element = driver.findElementById(prefix + ":id/voipcall_end_btn");
            element.click();
            break;
        }
    }

    // join device
    @Test
    public void GC_TC_03 () throws InterruptedException {

        //join device
        while (1==1) {
            if (driver.findElementByXPath("//android.widget.TextView[@text='"+start_device+"']").isDisplayed())
                Thread.sleep(10000);
             break;
        }
    }

    @AfterTest
    public void quitDriver () {
        driver.quit();
    }
}
