package com.qx.learn.CommonTools;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public final class ArgumentValidatorUtility {

    private ArgumentValidatorUtility(){}

    public static void isTrue(boolean expression, String errorMessage) {
        if (StringUtility.isNullOrWhiteSpace(errorMessage)) {
            throw new IllegalArgumentException("errorMessage");
        }

        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void hasText(String arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (StringUtility.isNullOrWhiteSpace(arg)) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotNullOrEmpty(String arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (StringUtility.isNullOrEmpty(arg)) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(Collection<?> arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.isEmpty()) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(Map<?, ?> arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (MapUtility.isNullOrEmpty(arg)) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static <T> void isNotEmpty(T[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(int[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(long[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(float[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(double[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(byte[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(short[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(char[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotEmpty(boolean[] arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null || arg.length <= 0) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void isNotNull(Object arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }

        if (arg == null) {
            throw new IllegalArgumentException(argName);
        }
    }

    public static void hasNoNullElement(Collection<?> arg, String argName) {
        hasNoInvalidElement(arg, argName, item -> item == null, "There is null element in " + argName);
    }

    public static <T> void hasNoInvalidElement(Collection<T> arg, String argName, Predicate<T> invalidPredicate) {
        hasNoInvalidElement(arg, argName, invalidPredicate, "There is invalid element in " + argName);
    }

    public static <T> void hasNoInvalidElement(Collection<T> arg, String argName, Predicate<T> invalidPredicate, String errorMessage) {
        isNotEmpty(arg, argName);

        if (invalidPredicate == null) {
            throw new IllegalArgumentException("invalidPredicate");
        }
        if (StringUtility.isNullOrWhiteSpace(errorMessage)) {
            throw new IllegalArgumentException("errorMessage");
        }

        for (T item : arg) {
            if (invalidPredicate.test(item)) {
                throw new IllegalArgumentException(errorMessage);
            }
        }
    }

    public static <T> void hasNoNullElement(T[] arg, String argName) {
        hasNoInvalidElement(arg, argName, item -> item == null, "There is null element in " + argName);
    }

    public static <T> void hasNoInvalidElement(T[] arg, String argName, Predicate<T> invalidPredicate) {
        hasNoInvalidElement(arg, argName, invalidPredicate, "There is invalid element in " + argName);
    }

    public static <T> void hasNoInvalidElement(T[] arg, String argName, Predicate<T> invalidPredicate, String errorMessage) {
        isNotEmpty(arg, argName);

        if (invalidPredicate == null) {
            throw new IllegalArgumentException("invalidPredicate");
        }
        if (StringUtility.isNullOrWhiteSpace(errorMessage)) {
            throw new IllegalArgumentException("errorMessage");
        }

        for (T item : arg) {
            if (invalidPredicate.test(item)) {
                throw new IllegalArgumentException(errorMessage);
            }
        }
    }


    public static void hasNoNullOrEmptyKeyAndValue(Map<String, String> arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }
        isNotEmpty(arg, argName);
        for (Map.Entry<String, String> entry : arg.entrySet()) {
            if (StringUtility.isNullOrEmpty(entry.getKey())) {
                throw new IllegalArgumentException("keys cannot be null or empty string in the map " + argName);
            }
            if (StringUtility.isNullOrEmpty(entry.getValue())) {
                throw new IllegalArgumentException("values cannot be null or empty string in the map " + argName);
            }
        }
    }

    public static <T> void hasNoNullKeyAndValue(Map<String, T> arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }
        isNotEmpty(arg, argName);
        for (Map.Entry<String, T> entry : arg.entrySet()) {
            if (StringUtility.isNullOrEmpty(entry.getKey())) {
                throw new IllegalArgumentException("keys cannot be null or empty string in the map " + argName);
            }
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("values cannot be null in the map " + argName);
            }
        }
    }

    public static void hasNoNullOrEmptyValue(Set<String> arg, String argName) {
        if (StringUtility.isNullOrWhiteSpace(argName)) {
            throw new IllegalArgumentException("argName");
        }
        isNotEmpty(arg, argName);
        for (String val : arg) {
            if (StringUtility.isNullOrEmpty(val)) {
                throw new IllegalArgumentException("values cannot be null or empty string in the set " + argName);
            }
        }
    }
}
