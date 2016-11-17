package com.kahramani.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kahramani on 11/16/2016.
 */
public class DateUtils extends ArgumentUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_DATE_LONG_FORMAT = "yyyyMMdd";
    private static final String DEFAULT_DATE_TIME_LONG_FORMAT = "yyyyMMddHHmmss";
    // TODO fill Comments
    public static Date getCurrentDate() {
        return truncTime(Calendar.getInstance());
    }

    public static String getFormattedCurrentDate() {
        return formatDate(Calendar.getInstance().getTime(), DEFAULT_DATE_FORMAT);
    }

    public static Date getCurrentDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static String getFormattedCurrentDateTime() {
        return formatDate(Calendar.getInstance().getTime(), DEFAULT_DATE_TIME_FORMAT);
    }

    public static java.sql.Timestamp getCurrentSqlTimeStamp() {
        return new java.sql.Timestamp(new Date().getTime());
    }

    public static int getCurrentDateField(DateFormat.Field f) {
        return getCalendarField(Calendar.getInstance(), f);
    }

    public static int getCurrentDateFieldBack(DateFormat.Field f, int back) {
        Calendar cal = Calendar.getInstance();
        if(!checkIfNegativeOrZero(back))
            back = back * -1;
        cal.add(f.getCalendarField(), back);

        return getCalendarField(cal, f);
    }

    public static Long getCurrentDateAsLong(String format) {
        return Long.valueOf(formatDate(Calendar.getInstance().getTime(), format));
    }

    public static Long getCurrentDateAsLong() {
        return Long.valueOf(formatDate(Calendar.getInstance().getTime(), DEFAULT_DATE_LONG_FORMAT));
    }

    public static Long getCurrentDateTimeAsLong() {
        return Long.valueOf(formatDate(Calendar.getInstance().getTime(), DEFAULT_DATE_TIME_LONG_FORMAT));
    }

    public static Long getDateDayBackAsLong(int dayBack, String format) {
        return Long.valueOf(formatDate(getDayBack(dayBack).getTime(), format));
    }

    public static Long getDateDayBackAsLong(int dayBack) {
        return Long.valueOf(formatDate(getDayBack(dayBack).getTime(), DEFAULT_DATE_LONG_FORMAT));
    }

    public static Long getDateTimeDayBackAsLong(int dayBack) {
        return Long.valueOf(formatDate(getDayBack(dayBack).getTime(), DEFAULT_DATE_TIME_LONG_FORMAT));
    }

    public static Date truncTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static String formatDateTime(Calendar cal, String dateTimeFormat) {
        return formatDate(cal.getTime(), dateTimeFormat);
    }

    public static String formatDateTime(Date date, String dateTimeFormat) {
        return formatDate(date, dateTimeFormat);
    }

    public static String formatCurrentDateTime(String dateTimeFormat) {
        return formatDate(Calendar.getInstance().getTime(), dateTimeFormat);
    }

    private static String formatDate(Date date, String format) {
        if(isNull(date)) {
            logger.error("date cannot be null");
            return null;
        } else if(isEmptyOrNull(format)) {
            logger.error("format cannot be null or empty");
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    private static Calendar getDayBack(int dayBack) {
        Calendar cal = Calendar.getInstance();
        if(!checkIfNegativeOrZero(dayBack))
            dayBack = dayBack * -1;
        cal.add(Calendar.DATE, dayBack);
        return cal;
    }

    private static int getCalendarField(Calendar cal, DateFormat.Field f) {
        if(isNull(cal)) {
            logger.error("cal cannot be null");
            return -1;
        } if(isNull(f)) {
            logger.error("field cannot be null");
            return -1;
        }

        return cal.get(f.getCalendarField());
    }
}
