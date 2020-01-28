import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.touch.ActionOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.TouchAction;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
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
import utils.CallUtils;
import utils.CommonUtils;
import utils.GroupCallUtils;
import utils.Values;

public class Call {

    public AndroidDriver driver = null;
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public WebElement element;
    public String prefix = "jp.naver.line.android";

    // Utils instance
    private CallUtils CallUtils = new CallUtils();
    private CommonUtils CommonUtils = new CommonUtils();
    private Values Values = new Values();

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
            driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

            // Todo Call API class
        }
    }

    //app start to call tab
    @Test
    public void TC_01_launchApp () throws InterruptedException {

        Assert.assertEquals(CommonUtils.getTextCallTab(driver), "Calls");
        CommonUtils.clickCallTab(driver);
    }

    // keypad
    @Test
    public void TC_02_launchApp () throws InterruptedException {

        element = driver.findElementById(prefix + ":id/header_title");
        String text2 = element.getText();
        Assert.assertEquals(text2, "Calls");

        element = driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Keypad button\"]/android.widget.ImageView");
        element.click();

        Thread.sleep(1000);

        element = driver.findElementById(prefix + ":id/input_number_edit");
        String text3 = element.getText();
        Assert.assertEquals(text3, "+81");

        element.sendKeys("####"); // phone number
        Thread.sleep(1000);
    }

    int count = 1;

    // call to end loop
    @Test
    public void TC_03_launchApp() throws InterruptedException {

        do {
            element = driver.findElementById(prefix + ":id/keypad_call_button");
            element.click();

            Thread.sleep(5000);

            //calling verifiy
            element = driver.findElementById(prefix + ":id/line_out_name_text");
            String text4 = element.getText();
            Assert.assertEquals(text4, "07041351187");

            //calling verifiy
            while(1==1) {
                if (driver.findElementById(prefix + ":id/line_out_status_text").isDisplayed())
                    Thread.sleep(70000);
                element = driver.findElementById(prefix + ":id/voipcall_end_btn");
                element.click();
                break;
            }

            count++;
        } while (count < 4); // loop count
    }

    @AfterTest
    public void quitDriver () {
        driver.quit();
    }
}
