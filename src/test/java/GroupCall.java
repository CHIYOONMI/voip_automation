import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.CommonUtils;
import utils.GroupCallUtils;
import utils.Values;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GroupCall {

    private AndroidDriver driver = null;
    private DesiredCapabilities capabilities = new DesiredCapabilities();

    // Utils instance
    private GroupCallUtils GruopCallUtils = new GroupCallUtils();
    private CommonUtils CommonUtils = new CommonUtils();
    private Values Values = new Values();

    @BeforeClass(alwaysRun = true)
    @Parameters({"platform", "port", "udid", "device", "ver", "autoname", "systemp"})
    public void setUp(/* String ip, */ String platform, String port, String udid, String device, String ver, String autoname, String systemp) throws MalformedURLException, InterruptedException {

        if (platform.equals("window")) {
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

        Assert.assertEquals(GruopCallUtils.getTextCallTab(driver), "Calls");
        GruopCallUtils.clickCallTab(driver);
        GruopCallUtils.selectRoomName(driver, Values.ROOM_NAME);
        GruopCallUtils.selectCallType(driver, Values.CALL_TYPE_VIDEO_CALL);
        GruopCallUtils.clickTrunOffMicBtn(driver);
    }


    // start device
    @Test
    public void GC_TC_02 () throws InterruptedException {

        while(true) {
            if(GruopCallUtils.isDisplayedDevice(driver, Values.GALAXY_S10)) {
                GruopCallUtils.tapSomething(driver);
                CommonUtils.sleep(1000);
                GruopCallUtils.clickVoipCallEndBtn(driver);
                break;
            }
        }
    }

    // join device
    @Test
    public void GC_TC_03 () throws InterruptedException {

        while (true) {
            if (GruopCallUtils.isDisplayedDevice(driver, Values.GALAXY_S6)) {
                CommonUtils.sleep(1000);
                break;
            }
        }
    }

    @AfterTest
    public void quitDriver () {
        driver.quit();
    }
}
