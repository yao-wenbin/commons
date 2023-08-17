package io.github.yaowenbin.commons.string;

/**
 *  
 *  
 */
public class Strings {

    private Strings() {}

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        return str == null || isEmpty(str.trim());
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }


    // TODO
    public static String format(String template, String... params) {

        return template;
    }
}
