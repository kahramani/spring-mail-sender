package com.kahramani.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by kahramani on 11/16/2016.
 */
public class ArgumentUtils {

    private static final Logger logger = LoggerFactory.getLogger(ArgumentUtils.class);

    /**
     * to check empty/null status of the given
     * @param t wanted to check empty/null
     * @param <T> any child class of CharSequence
     * @return a boolean which refers that the given value is empty/null or not
     */
    public static <T extends CharSequence> boolean isEmptyOrNull(T t) {
        return (t == null || t.length() == 0);
    }

    /**
     * to check empty/null status of the given
     * @param t wanted to check empty/null
     * @param <T> any child class of Collection
     * @return a boolean which refers that the given value is empty/null or not
     */
    public static <T extends Collection> boolean isEmptyOrNull(T t) {
        return (t == null || t.isEmpty());
    }

    /**
     * to check empty/null status of the given
     * @param t wanted to check empty/null
     * @param <T> any child class of Map
     * @return a boolean which refers that the given value is empty/null or not
     */
    public static <T extends Map> boolean isEmptyOrNull(T t) {
        return (t == null || t.isEmpty());
    }

    /**
     * to check null status of the given Object
     * @param o wanted to check null
     * @return a boolean which refers that the given value is null or not
     */
    public static boolean isNull(Object o) {
        return o == null;
    }

    /**
     * to check if the given int is negative or zero
     * @param i wanted to check
     * @return a boolean which refers that the given value is negative/zero or not
     */
    public static boolean checkIfNegativeOrZero(int i) {
        return (i <= 0);
    }

    /**
     * to split a list into {partitionCount} sub-lists
     * @param list wanted to split
     * @param partitionCount how many partition created at the end
     * @return a List of List whose parent list has {partitionCount} sub-lists
     */
    public static List<List> splitListByPartitionCount(List list, int partitionCount) {
        if (isEmptyOrNull(list)) {
            logger.error("list cannot be null or empty");
            return null;
        } else if (checkIfNegativeOrZero(partitionCount)) {
            logger.error("partitionCount cannot be 0 or lower");
            return null;
        }

        int listSize = list.size();
        int partitionSize = (int) Math.ceil((double) listSize / partitionCount);

        return splitListByPartitionSize(list, partitionSize);
    }

    /**
     * to split a list into equal sized lists having size of partitionSize each
     * @param list wanted to split
     * @param partitionSize size of every partition created from the list
     * @return a List of List whose sub-lists has the size of {partitionSize} equally
     */
    public static List<List> splitListByPartitionSize(List list, int partitionSize) {
        if (isEmptyOrNull(list)) {
            logger.error("list cannot be null or empty");
            return null;
        } else if (checkIfNegativeOrZero(partitionSize)) {
            logger.error("partitionSize cannot be 0 or lower");
            return null;
        }

        List<List> partitionList = new ArrayList<List>();
        for (int i = 0; i < list.size(); i += partitionSize) {
            List subList = new ArrayList(list.subList(i, Math.min(i + partitionSize, list.size())));
            partitionList.add(subList);
        }
        return partitionList;
    }
}
