package utils;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;

public class CallUtils {
    private String prefix = "jp.naver.line.android";
    private String Layout = "//android.widget.LinearLayout[@content-desc=";

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
     *  Android back button - common으로 이동?
     */
    public void androidBackKey(AndroidDriver driver) {
        driver.navigate().back();
    }


    /**
     *  예외처리 : resume 버튼
     */
    public void tapResume(AndroidDriver driver) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(795, 1310)).release().perform();
    }

    /**
     *  Status check(reward)
     */
    public boolean isDisplayedStatusReward(AndroidDriver driver){
        return driver.findElementByXPath(Layout + "\"Switch to paid plan mode\"]/android.widget.ImageView[2]").isDisplayed();
    }

    /**
     *  Status check(paid)
     */
    public boolean isDisplayedStatuspaid(AndroidDriver driver){
        return driver.findElementByXPath(Layout + "\"Switch to LINE Out Free mode\"]/android.widget.ImageView[2]").isDisplayed();
    }

    /**
     *  Status Switch Button Reward to Paid
     */
    public void StatusButtonReward(AndroidDriver driver) {
        driver.findElementByXPath(Layout + "\"Switch to paid plan mode\"]/android.widget.ImageView[3]").click();
    }

    /**
     *  Status Switch Button Paid to Reward
     */
    public void StatusButtonPaid(AndroidDriver driver) {
        driver.findElementByXPath(Layout + "\"Switch to paid plan mode\"]/android.widget.ImageView[3]").click();
    }

    /**
     *  Status change popup Reward to Paid
     */
    public void StatusPopupReward(AndroidDriver driver) {
        driver.findElementById(prefix + ":id/line_out_mode_checkbox").click();
    }

    /**
     *  Status change popup Paid to Reward
     */
    public void StatusPopupPaid(AndroidDriver driver) {
        driver.findElementById(prefix + ":id/free_mode_checkbox").click();
    }

    /**
     *  Status Switch Button
     */
    public void StatusButton(AndroidDriver driver) {
        driver.findElementById(prefix + ":id/keypad_mode_layout").click();
    }

    /**
     *  예외처리 resume point
     */
    public void Resumepoint(AndroidDriver driver) {
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(744, 1268)).release().perform();
    }

    /**
     *  Ads limit
     */
    public boolean RewardLimitText(AndroidDriver driver) {
        return driver.findElementById(prefix + ":id/title_line_out_free").isDisplayed();
    }

    /**
     *  Ads Limit Cancel Button
     */
    public void LimitCancelbutton(AndroidDriver driver) {
        driver.findElementById(prefix + ":id/button_second").click();
    }
























}
