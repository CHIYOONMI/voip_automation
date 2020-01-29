package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;

public class CallUtils {
    private String prefix = "jp.naver.line.android";

    /**
     * 콜 탭에서 타이틀 확인
     * @return 취득한 텍스트
     */
    public String getTextHeaderTitle(AndroidDriver driver) {
        WebElement element = driver.findElementById(prefix + ":id/header_title");;
        return element.getText();
    }

    /**
     *  키패드 아이콘 클릭
     */
    public void clickKeypadButton(AndroidDriver driver){
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Keypad button\"]/android.widget.ImageView").click();
    }

    /**
     *  발신 할 번호 입력
     */
    public void SendPhoneNumber(AndroidDriver driver, String PhoneNumber) {
        driver.findElementById(prefix + ":id/input_number_edit").sendKeys(PhoneNumber); // Phone number values???
    }

    /**
     *  Call button
     */
    public void ClickCallButton(AndroidDriver driver) {
        driver.findElementById(prefix + ":id/keypad_call_button").click();
    }


    /**
     *  LINECall 화면에서 LINEOut Status 표시되는지 확인
     */
    public boolean isDisplayedCallStatus(AndroidDriver driver){
        return driver.findElementById(prefix + ":id/line_out_status_text").isDisplayed();
    }

    /**
     *  Call button
     */
    public void ClickEndButton(AndroidDriver driver) {
        driver.findElementById(prefix + ":id/voipcall_end_btn").click();
    }

    /**
     *  LINECall Ad 화면 확인
     */
    public boolean isDisplayedAds(AndroidDriver driver){
        return driver.findElementByXPath("//android.widget.Image").isDisplayed();
    }


    /**
     *  LINECall 화면에서 LINEOut Status 표시되는지 확인
     */
    public void androidBackKey(AndroidDriver driver) {
        driver.navigate().back();
    }


    /**
     *  예외처리 : resume 버튼
     */
    public void tapSomething(AndroidDriver driver) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(795, 1310)).release().perform();
    }


























}
