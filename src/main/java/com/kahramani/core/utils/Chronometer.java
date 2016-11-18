package com.kahramani.core.utils;

/**
 * Created by kahramani on 11/16/2016.
 */
public class Chronometer {

    private final int SECONDS = 1000;
    private final int MINUTE = 60000;
    private final int HOUR = 3600000;
    private final int DAY = 86400000;
    private String abbrMillisecond = "ms";
    private String abbrSeconds = "secs";
    private String abbrMinute = "mins";
    private String abbrHour = "hours";
    private String abbrDay = "days";

    private long begin, end;

    public Chronometer() {
    }

    public Chronometer(String abbrMillisecond,
                       String abbrSeconds,
                       String abbrMinute,
                       String abbrHour,
                       String abbrDay) {
        this.abbrMillisecond = abbrMillisecond;
        this.abbrSeconds = abbrSeconds;
        this.abbrMinute = abbrMinute;
        this.abbrHour = abbrHour;
        this.abbrDay = abbrDay;
    }

    /**
     * to start chronometer
     * @return a long which gives the start time in millis
     */
    public long start() {
        this.begin = System.currentTimeMillis();
        return this.begin;
    }

    /**
     * to stop chronometer
     * @return a long which gives the stop time in millis
     */
    public long stop() {
        this.end = System.currentTimeMillis();
        return this.end;
    }

    /**
     * to clear chronometer's begin and end parameters
     */
    public void clear() {
        this.begin = 0;
        this.end = 0;
    }

    /**
     * to get tour duration without stopping chronometer
     * @return a long which gives how much time elapsed from the beginning
     */
    public long getTourDuration() {
        return System.currentTimeMillis() - this.begin;
    }

    /**
     * to get elapsed time in millis
     * @return a long which gives how much time elapsed from the beginning
     */
    public long getElapsedTimeInMillis() {
        return this.end - this.begin;
    }

    /**
     * to get duration in proper string format after chronometer stopped
     * @return a String which is the duration converted to the proper format
     */
    public String getDuration() {
        return getElapsedTimeInStr(getElapsedTimeInMillis());
    }

    /**
     * to get duration in proper string format after chronometer stopped
     * @param elapsedTime which wanted to convert proper string format
     * @return a String which is the duration converted to the proper format
     */
    public String getDuration(long elapsedTime) {
        return getElapsedTimeInStr(elapsedTime);
    }

    /**
     * to get elapsed time in proper string format after chronometer stopped
     * @param duration which wanted to convert proper string format
     * @return a String which gives elapsed time in proper format
     */
    private String getElapsedTimeInStr(long duration) {

        StringBuilder timeStr = new StringBuilder("");

        if(duration == 0) {
            timeStr.append(0).append(" ").append(abbrMillisecond);
            return timeStr.toString();
        }

        if(SECONDS > duration) {
            timeStr.append(duration).append(" ").append(abbrMillisecond);
        } else if (MINUTE > duration) {
            int sec = (int) Math.floor((double) (duration / SECONDS));
            int ms = (int) duration - SECONDS*sec;
            timeStr.append(sec).append(" ").append(abbrSeconds).append(" ")
                    .append(ms).append(" ").append(abbrMillisecond);
        } else if (HOUR > duration) {
            int min = (int) Math.floor((double) (duration / MINUTE));
            int remainder = (int) duration - MINUTE*min;
            int sec = (int) Math.floor((double) (remainder / SECONDS));
            timeStr.append(min).append(" ").append(abbrMinute).append(" ")
                    .append(sec).append(" ").append(abbrSeconds);
        } else if (DAY > duration) {
            int hour = (int) Math.floor((double) (duration / HOUR));
            int remainder = (int) duration - HOUR*hour;
            int min = (int) Math.floor((double) (remainder / MINUTE));
            remainder -= MINUTE*min;
            int sec= (int) Math.floor((double) (remainder / SECONDS));
            timeStr.append(hour).append(" ").append(abbrHour).append(" ")
                    .append(min).append(" ").append(abbrMinute).append(" ")
                    .append(sec).append(" ").append(abbrSeconds);
        } else if (DAY > duration) {
            int day = (int) Math.floor((double) (duration / DAY));
            int remainder = (int) duration - DAY*day;
            int hour = (int) Math.floor((double) (remainder / HOUR));
            remainder -= HOUR*hour;
            int min = (int) Math.floor((double) (remainder / MINUTE));
            remainder -= MINUTE*min;
            int sec= (int) Math.floor((double) (remainder / SECONDS));
            timeStr.append(day).append(" ").append(abbrDay).append(" ")
                    .append(hour).append(" ").append(abbrHour).append(" ")
                    .append(min).append(" ").append(abbrMinute).append(" ")
                    .append(sec).append(" ").append(abbrSeconds);
        } else {
            timeStr.append(0).append(" ").append(abbrMillisecond);
        }

        return timeStr.toString();
    }
}
