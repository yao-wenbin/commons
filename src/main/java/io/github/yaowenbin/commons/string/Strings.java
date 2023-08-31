package io.github.yaowenbin.commons.string;

import io.github.yaowenbin.commons.list.Arrays;

public class Strings {

    private Strings() {}

    public static final String EMPTY = "";

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
    public static String format(String template, Object... params) {
        if (Strings.isBlank(template) || Arrays.isEmpty(params)) {
            return template;
        }

        String placeHolder = "{}";
        int placeHolderLen = placeHolder.length();
        StringBuilder sb = new StringBuilder();
        int templateLen = template.length();
        int handlePos = 0;
        for (int i = 0; i < params.length; i++) {
            int placeHolderIndex = template.indexOf(placeHolder, handlePos);
            if (placeHolderIndex == -1) {
                // no placeholder in template.
                if (handlePos == 0) {
                    return template;
                } else {
                // no more placeholder in template, just append rest of template and return.
                    sb.append(template, handlePos, templateLen);
                    return sb.toString();
                }
            }
            // append chars before placeHolderIndex
            sb.append(template, handlePos, placeHolderIndex);
            sb.append(params[i].toString());
            handlePos = placeHolderIndex + placeHolderLen;
        }
        // handle rest strings.
        sb.append(template, handlePos, templateLen);
        return sb.toString();
    }

    public static String upperFirstChar(String fieldName) {
        if (Strings.isBlank(fieldName)) {
            return fieldName;
        }
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
