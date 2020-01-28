package utils;

public class CommonUtils {

    /**
     *  지정한 초 만큼 일시중지
     *  @param millis 일시정지시간(초)
     */
    public void sleep(int millis) throws InterruptedException{
        Thread.sleep(millis);
    }

}
