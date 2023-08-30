package io.github.yaowenbin.commons.reflect;

import java.lang.reflect.Method;

public class Reflects {

    /**
     * set method or field accessible.
     * reminder: accessible of Method and Field in java.lang.reflect package doesn't mean a method or field whether access.
     *   instead, it's means JVM whether exec type-safe check.
     *   so set accessible to true will skip the type-safe check that will increase performance.
     * @param method
     */
    public static void setAccessible(Method method) {
        if (method == null || method.isAccessible()) {
            return;
        }
        method.setAccessible(true);
    }

    /**
     * check access modifier of method is public.
     * protected | private | public
     * 4         | 2       | 1
     * @param method
     */
    public static boolean isPublic(Method method) {
        return (method.getModifiers() & 1) == 1;
    }
}
