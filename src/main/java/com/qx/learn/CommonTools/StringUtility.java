package com.qx.learn.CommonTools;

import javax.xml.bind.DatatypeConverter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class StringUtility {

    private StringUtility() {
    }

    public static boolean isNotEmpty(String value) {
        return !isNullOrEmpty(value);
    }

    public static boolean isNullOrEmpty(String value) {
        return (value == null || value.isEmpty());
    }

    public static boolean equalsIgnoreNullOrEmpty(String left, String right) {
        if (isNullOrEmpty(left) && isNullOrEmpty(right)) {
            return true;
        }

        if (isNullOrEmpty(left) || isNullOrEmpty(right)) {
            return false;
        }

        return left.equalsIgnoreCase(right);
    }

    public static boolean isNullOrWhiteSpace(String value) {
        if (isNullOrEmpty(value)) {
            return true;
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String join(List<String> lst, String separator) {
        if (lst == null) {
            throw new IllegalArgumentException("lst");
        }
        if (separator == null) {
            throw new IllegalArgumentException("separator");
        }

        StringBuilder result = new StringBuilder();
        if (lst.size() == 0) {
            return result.toString();
        }

        String item = lst.get(0);
        result.append(item != null ? item : "");

        for (int i = 1; i < lst.size(); i++) {
            result.append(separator);
            item = lst.get(i);
            result.append(item != null ? item : "");
        }

        return result.toString();
    }

    public static <T> String join(List<T> list, Function<T, String> toStringFunc, String separator) {
        if (ListUtility.isNullOrEmpty(list)) {
            throw new IllegalArgumentException("list");
        }

        if (toStringFunc == null) {
            throw new IllegalArgumentException("toStringFunc");
        }
        return join(list.stream().map(toStringFunc).collect(Collectors.toList()), separator);
    }

    public static String trimToEmpty(String value) {
        return value == null ? "" : value.trim();
    }

    public static String trimNullableString(String value) {
        if (value == null) {
            return value;
        } else {
            return value.trim();
        }
    }

    public static String trimEnd(String source, String suffix) {
        if (StringUtility.isNullOrEmpty(source)) {
            return source;
        }
        if (StringUtility.isNullOrEmpty(suffix)) {
            return source;
        }

        int suffixLength = suffix.length();
        int sourceLength = source.length();
        if (suffixLength > sourceLength) {
            return source;
        }

        int lastIndex = source.lastIndexOf(suffix);
        if (lastIndex == -1) {
            return source;
        } else if (lastIndex == 0) {
            return "";
        } else if (lastIndex + suffixLength == sourceLength) {
            return source.substring(0, lastIndex);
        }

        return source;
    }

    public static String toBase64String(String source) {
        if (StringUtility.isNullOrEmpty(source)) {
            return source;
        }
        String result;
        try {
            result = DatatypeConverter.printBase64Binary(source.getBytes(DEFAULT_CHARSET));
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return result;
    }

    public static String fromBase64String(String base64) {
        if (StringUtility.isNullOrEmpty(base64)) {
            return base64;
        }

        try {
            return new String(DatatypeConverter.parseBase64Binary(base64), DEFAULT_CHARSET);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static String getPrivacyString(String plainText, char privacyChar, int headLength, int endLength) {
        if (StringUtility.isNullOrEmpty(plainText)) {
            throw new IllegalArgumentException("plainText");
        }
        if (headLength < 0) {
            throw new IllegalArgumentException("headLength");
        }
        if (endLength < 0) {
            throw new IllegalArgumentException("endLength");
        }

        int plainTextLength = plainText.length();
        if (headLength + endLength >= plainTextLength) {
            throw new IllegalArgumentException("headLength and endLength is invalid.");
        }

        char[] plainTexts = new char[plainTextLength];

        for (int i = 0; i < headLength; i++) {
            plainTexts[i] = plainText.charAt(i);
        }
        for (int i = headLength; i < plainTextLength - endLength; i++) {
            plainTexts[i] = privacyChar;
        }
        for (int i = plainTextLength - endLength; i < plainTextLength; i++) {
            plainTexts[i] = plainText.charAt(i);
        }
        return new String(plainTexts);
    }

    private static final String DEFAULT_CHARSET = "utf-8";
}
