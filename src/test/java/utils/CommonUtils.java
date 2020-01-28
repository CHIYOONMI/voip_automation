package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CommonUtils {

    private String FOTTER_MENU_PREFIX = "(//android.view.ViewGroup[@content-desc=\"@{bottomNavigationBarButtonViewModel.contentDescription\"])[4]";


    /**
     * 콜 탭에서 텍스트를 취득 - 홈 화면에서 콜탭인지 뉴스인지 확인(디폴트가 뉴스임)
     * @return 취득한 텍스트
     */
    public String getTextCallTab(AndroidDriver driver) {
        WebElement element = driver.findElementByXPath(FOTTER_MENU_PREFIX + "/android.widget.TextView");
        return element.getText();
    }

    /**
     *  콜 탭 이동
     */
    public void clickCallTab(AndroidDriver driver){
        driver.findElementByXPath(FOTTER_MENU_PREFIX + "/android.view.View").click();
    }

    /**
     *  지정한 초 만큼 일시중지
     *  @param millis 일시정지시간(초)
     */
    public void sleep(int millis) throws InterruptedException{
        Thread.sleep(millis);
    }

}
