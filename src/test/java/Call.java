import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.CallUtils;
import utils.Call_Loop;
import utils.CommonUtils;
import utils.Values;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Call {

    public AndroidDriver driver = null;
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    // Utils instance
    private CallUtils CallUtils = new CallUtils();
    private CommonUtils CommonUtils = new CommonUtils();
    private Values Values = new Values();
    private Call_Loop Call_Loop = new Call_Loop();

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
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            // Todo Call API class
        }
    }

    //app start to call tab
    @Test
    public void Call_TC_01 () throws InterruptedException {

        Assert.assertEquals(CommonUtils.getTextCallTab(driver), "Calls");
        CommonUtils.clickCallTab(driver);
    }

    // keypad
    @Test
    public void Call_TC_02 () throws InterruptedException {

        Assert.assertEquals(CallUtils.getTextHeaderTitle(driver), "Calls");
        CallUtils.clickKeypadButton(driver);
        CommonUtils.sleep(1000);

        CallUtils.SendPhoneNumber(driver, Values.PHONENUMBER);
        CommonUtils.sleep(1000);
    }

    int count = 1;
    @Test
    public void Call_TC_03 () throws InterruptedException {
        do {
            Call_Loop.Call_loop_paid(driver);
            Call_Loop.Call_loop_reward(driver);

            count++;
        } while (count < 7);
    }

    @AfterTest
    public void quitDriver () {
        driver.quit();
    }
}

























