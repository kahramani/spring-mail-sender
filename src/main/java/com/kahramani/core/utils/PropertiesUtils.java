package com.kahramani.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by kahramani on 11/16/2016.
 */
public class PropertiesUtils extends ArgumentUtils implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private Environment environment;

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    /**
     * to get property value with key
     * @param key property key whose value wanted to get
     * @return a String which is the value of the given key
     */
    public String getString(String key) {
        if(this.isEmptyOrNull(key)) {
            logger.error("Key cannot be null or empty");
            return null;
        }
        return this.environment.getProperty(key);
    }

    /**
     * to get boolean value and to handle exceptions
     * @param key property key whose value wanted to get
     * @param defaultValue default value to set if in case that property can not be found
     * @return a boolean which is the property value of the given key
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        String s = this.getString(key);
        if(this.isEmptyOrNull(s)) {
            return defaultValue;
        }

        return Boolean.parseBoolean(s.trim());
    }

    /**
     * to get int value and to handle exceptions
     * @param key property key whose value wanted to get
     * @param defaultValue default value to set if in case that property can not be found
     * @return an int which is the property value of the given key
     */
    public int getInt(String key, int defaultValue) {
        Number n = defaultValue;
        String prop = this.getString(key);
        try {
            n = this.parseNumber(prop);
        } catch (ParseException e) {
            logger.error("Failed to parse int value of " + prop, e);
        }
        if(this.isNull(n))
            return defaultValue;

        return n.intValue();
    }

    /**
     * to get long value and to handle exceptions
     * @param key property key whose value wanted to get
     * @param defaultValue default value to set if in case that property can not be found
     * @return a long which is the property value of the given key
     */
    public long getLong(String key, long defaultValue) {
        Number n = defaultValue;
        String prop = this.getString(key);
        try {
            n = this.parseNumber(prop);
        } catch (ParseException e) {
            logger.error("Failed to parse long value of " + prop, e);
        }
        if(this.isNull(n))
            return defaultValue;

        return n.longValue();
    }

    /**
     * to get double value and to handle exceptions
     * @param key property key whose value wanted to get
     * @param defaultValue default value to set if in case that property can not be found
     * @return a double which is the property value of the given key
     */
    public double getDouble(String key, double defaultValue) {
        Number n = defaultValue;
        String prop = this.getString(key);
        try {
            n = this.parseNumber(prop);
        } catch (ParseException e) {
            logger.error("Failed to parse double value of " + prop, e);
        }
        if(this.isNull(n))
            return defaultValue;

        return n.doubleValue();
    }

    /**
     * to get float value and to handle exceptions
     * @param key property key whose value wanted to get
     * @param defaultValue default value to set if in case that property can not be found
     * @return a float which is the property value of the given key
     */
    public float getFloat(String key, float defaultValue) {
        Number n = defaultValue;
        String prop = this.getString(key);
        try {
            n = this.parseNumber(prop);
        } catch (ParseException e) {
            logger.error("Failed to parse float value of " + prop, e);
        }
        if(this.isNull(n))
            return defaultValue;

        return n.floatValue();
    }

    /**
     * to get file content
     * @param filePathKey property file key which points the path of the file whose content wanted to read
     * @param readLineByLine read file line by line or char by char
     * @return a StringBuilder which has content of the file
     */
    public StringBuilder getFileContent(String filePathKey, boolean readLineByLine) {
        StringBuilder sb = null;
        BufferedReader br = null;
        InputStreamReader input = null;
        try {
            String filePath = this.getString(filePathKey);
            if (this.isEmptyOrNull(filePath)) {
                logger.error("filePath cannot be null or empty");
                return sb;
            }
            input = new InputStreamReader(PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath));
            br = new BufferedReader(input);
            int r;
            String str;
            sb = new StringBuilder("");
            if(readLineByLine) {
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
            } else {
                while ((r = br.read()) != -1) {
                    sb.append((char) r);
                }
            }
        } catch (IOException e) {
            logger.error("Failed to read content from file", e);
            sb = null;
        } finally {
            try {
                if (!this.isNull(br))
                    br.close();
                if (!this.isNull(input))
                    input.close();
            } catch (IOException e) {
                logger.error("Failed to close reader", e);
            }
        }
        return sb;
    }

    public StringBuilder getSqlQueryFromFile(String filePathKey, boolean readLineByLine) {
        return this.getFileContent(filePathKey, readLineByLine);
    }

    /**
     * to parse number from property
     * @param value property value
     * @param <T> any child class of Number
     * @return a T type which is the parsed number from the given value
     * @throws ParseException if cannot parse the given value
     */
    private <T extends Number> T parseNumber(String value) throws ParseException {
        if(this.isEmptyOrNull(value)) {
            logger.error("value cannot be null or empty");
            return null;
        }

        return (T) NumberFormat.getInstance().parse(value);
    }
}
