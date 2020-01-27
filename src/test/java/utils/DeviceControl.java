package utils;

import io.appium.java_client.android.AndroidDriver;

public class DeviceControl {

    public String prefix = "jp.naver.line.android";

    public void selectRoomName(AndroidDriver driver, String roomName) {
        driver.findElementByXPath("//android.widget.TextView[@text='" + roomName + "']").click();
    }

    public void selectCallType(AndroidDriver driver, String callType) {
        driver.findElementByXPath("//android.widget.TextView[@text='" + callType + "']").click();
    }

    public void clickTrunOffMicBtn(AndroidDriver driver) {
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Turn off microphone\"]").click();
    }

    public void clickVoipCallEndBtn(AndroidDriver driver) {
        driver.findElementById(prefix + ":id/voipcall_end_btn").click();
    }
}
