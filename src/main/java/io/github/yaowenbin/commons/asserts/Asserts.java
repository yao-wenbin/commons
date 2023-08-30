package io.github.yaowenbin.commons.asserts;

import io.github.yaowenbin.commons.string.Strings;

public class Asserts {

    public static void notNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    public static void notNull(Object obj, String msg) {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }

    public static void notNull(Object obj, String msg, Object... msgParams) {
        if (obj == null) {
            throw new NullPointerException(Strings.format(msg, msgParams));
        }
    }

}
