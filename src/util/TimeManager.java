package util;

/**
 * Created by CPU11630_LOCAL on 12/8/2018.
 */

public class TimeManager {
    private static final long startTime = 1544170180878L;
    public static int getTime(){
        return (int) ((System.currentTimeMillis() - startTime)/1000);
    }
}
