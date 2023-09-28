package io.github.yaowenbin.commons.datetime;

public class Timer {

    private long startTimestamp = System.currentTimeMillis();

    /**
     * @return start a timer.
     */
    public static Timer start() {
        return new Timer();
    }

    /**
     * @return timestamp between now and timer startTimestamp.
     */
    public long interval() {
        return System.currentTimeMillis() - startTimestamp;
    }

    /**
     * @return seconds between now and timer startTimestamp.
     */
    public long intervalSecond() {
        return interval() / 1000;
    }


    /**
     * @return minutes between now and timer startTimestamp.
     */
    public long intervalMinutes() {
        return intervalSecond() / 60;
    }

    /**
     * @return timestamp between now and timer startTimestamp and then reset startTimestamp.
     */
    public long intervalAndRest() {
        long interval = interval();
        startTimestamp = System.currentTimeMillis();
        return interval;
    }

}
