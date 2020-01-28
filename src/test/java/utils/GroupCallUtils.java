package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;


public class GroupCallUtils {

    private String PKG = "jp.naver.line.android";
    private String FOTTER_MENU_PREFIX = "(//android.view.ViewGroup[@content-desc=\"@{bottomNavigationBarButtonViewModel.contentDescription\"])[4]";
    public String join_device = "galaxy s6";
    public String start_device = "Galaxy S10";

    /**
     *  Room List에서 특정 룸 선택 후 클릭
     *  @param roomName 룸 이름
    */
    public void selectRoomName(AndroidDriver driver, String roomName) {
        driver.findElementByXPath("//android.widget.TextView[@text='" + roomName + "']").click();
    }

    /**
     *  특정 Call Type 클릭
     *  @param callType 콜 타입: Video call, Voice Call
     */
    public void selectCallType(AndroidDriver driver, String callType) {
        driver.findElementByXPath("//android.widget.TextView[@text='" + callType + "']").click();
    }

    /**
     *  마이크 끄기 버튼 클릭
     */
    public void clickTrunOffMicBtn(AndroidDriver driver) {
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Turn off microphone\"]").click();
    }

    /**
     *  voip 종료 버튼 클릭
     */
    public void clickVoipCallEndBtn(AndroidDriver driver) {
        driver.findElementById(PKG + ":id/voipcall_end_btn").click();
    }

    /**
     *  그룹콜 화면에서 특정 디바이스가 표시되는지 확인
     *  @param device 체크대상이 되는 디바이스 명
     *  @return 디바이스가 표시되면 true
     */
    public boolean isDisplayedDevice(AndroidDriver driver, String device){
        return driver.findElementByXPath("//android.widget.TextView[@text='" + device + "']").isDisplayed();
    }

    /**
     *  뭔가를 탭
     */
    public void tapSomething(AndroidDriver driver) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(274, 2124)).release().perform();
    }

    /**
     * 콜 탭에서 텍스트를 취득?
     * @return 취득한 텍스트
     */
    public String getTextCallTab(AndroidDriver driver) {
        WebElement element = driver.findElementByXPath(FOTTER_MENU_PREFIX + "/android.widget.TextView");
        return element.getText();
    }

    /**
     *  콜 탭 클릭?
     */
    public void clickCallTab(AndroidDriver driver){
        driver.findElementByXPath(FOTTER_MENU_PREFIX + "/android.view.View").click();
    }



}
