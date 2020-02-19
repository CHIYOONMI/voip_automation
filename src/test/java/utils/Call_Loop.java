package utils;

import org.openqa.selenium.NoSuchElementException;
import io.appium.java_client.android.AndroidDriver;


public class Call_Loop {

    private AndroidDriver driver = null;

    // Utils instance
    private CallUtils CallUtils = new CallUtils();
    private CommonUtils CommonUtils = new CommonUtils();
    private Values Values = new Values();


    /**
     *  paid call
     */

    public void Call_loop_paid(AndroidDriver driver) throws InterruptedException {
        CallUtils.StatusButton(driver);
        CommonUtils.sleep(1);

        CallUtils.StatusPopupPaid(driver);
        CallUtils.ClickCallButton(driver);

        CommonUtils.sleep(1000);

        while (1==1) {
            if (CallUtils.isDisplayedCallStatus(driver))
                CommonUtils.sleep(5000);
            CallUtils.ClickEndButton(driver);
            break;
        }
        CommonUtils.sleep(1000);

    }

    /**
     *  reward call
     */

    public void Call_loop_reward(AndroidDriver driver) throws InterruptedException {
        CallUtils.StatusButton(driver);
        CommonUtils.sleep(1000);

        CallUtils.StatusPopupReward(driver);
        CommonUtils.sleep(1000);

        CallUtils.ClickCallButton(driver);
        CommonUtils.sleep(20000);

        try {
            if (CallUtils.RewardLimitText(driver)) ;
            {
                CallUtils.LimitCancelbutton(driver);
            }
        } catch (NoSuchElementException e) {
            while (1==1) {
                CallUtils.androidBackKey(driver);
                CommonUtils.sleep(1000);

                try {
                    if (CallUtils.isDisplayedCallStatus(driver)) ;
                    {
                        CommonUtils.sleep(5000);
                        CallUtils.ClickEndButton(driver);
                        break;
                    }
                } catch (NoSuchElementException f) {
                    CallUtils.Resumepoint(driver);
                    CommonUtils.sleep(5000);
                }
            }
        }
    }

}


