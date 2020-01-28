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
     *  마이크 끄기 버튼 클릭 - 하울링 방지
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
     *  뭔가를 탭 : 통화 시작 후 일정시간 경과하면 화면에서 종료아이콘 사라짐 - 종료하려면 화면 탭해서 아이콘 표시 필요
     */
    public void tapSomething(AndroidDriver driver) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(274, 2124)).release().perform();
    }

    /**
     * 콜 탭에서 텍스트를 취득? - 홈 화면에서 콜탭인지 뉴스인지 확인(디폴트가 뉴스임) - 커먼에 카피함
     * @return 취득한 텍스트
     */
    public String getTextCallTab(AndroidDriver driver) {
        WebElement element = driver.findElementByXPath(FOTTER_MENU_PREFIX + "/android.widget.TextView");
        return element.getText();
    }

    /**
     *  콜 탭 클릭? - 라인 홈화면에서 콜탭으로 이동(라인콜과 공통 동작 - 커먼으로 이동해도 될 듯 - 커먼에 카피함 - Call은 커먼에서 사용)
     */
    public void clickCallTab(AndroidDriver driver){
        driver.findElementByXPath(FOTTER_MENU_PREFIX + "/android.view.View").click();
    }



}
