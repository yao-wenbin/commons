package io.github.yaowenbin.commons.reflect;

import io.github.yaowenbin.commons.asserts.Asserts;
import io.github.yaowenbin.commons.string.Strings;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static Method[] getPublicMethods(Class<?> clz) {
        return getPublicMethods(clz, true);
    }

    /**
     * @return methods contains
     * 1. any access modifier in clz.
     * 2. any public methods in clz supper.
     * same method name will return the one closet for clz.
     */
    public static Method[] getPublicMethods(Class<?> clz, boolean withSuppers) {
        Asserts.notNull(clz, "clz should not be null.");

        Set<Method> methods = new HashSet<>();
        Class<?> currentClz = clz;
        while (currentClz != null) {
            methods.addAll(Arrays.asList(clz.getDeclaredMethods()));
            currentClz = withSuppers ? currentClz.getSuperclass() : null;
        }
        return methods.toArray(new Method[0]);
    }

    public static Field[] fields(Class<?> clz) {
        return fields(clz, true);
    }

    public static Field[] fields(Class<?> clz, boolean withSuppers) {
        Asserts.notNull(clz, "clz should not be null.");

        List<Field> fields = new ArrayList<>();
        Class<?> currentClz = clz;
        while (currentClz != Object.class) {
            fields.addAll(Arrays.asList(currentClz.getDeclaredFields()));
            currentClz = withSuppers ? currentClz.getSuperclass() : null;
        }
        return fields.toArray(new Field[0]);
    }

    public static Object invoke(Object object, Method method, Object... args) {
        setAccessible(method);
        try {
            return method.invoke(object, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(Strings.format("method: {} cannot be invoke", method.getName()), e);
        }
    }


    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clz) {
        T res = null;

        Constructor<?>[] constructors = clz.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            constructor.setAccessible(true);
            try {
                res = (T) constructor.newInstance(getDefaultValsForClasses(parameterTypes));
                return res;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException ignore) {

            }
        }
        return null;
    }

    private static Object[] getDefaultValsForClasses(Class<?>... classes) {
        Object[] res = new Object[classes.length];
        for (int i = 0; i < classes.length; i++) {
            res[i] = getDefaultVal(classes[i]);
        }
        return res;
    }

    private static Object getDefaultVal(Class<?> clz) {
        if (clz.isPrimitive()) {
            return primitiveDefaultVal(clz);
        }
        return null;
    }

    private static Object primitiveDefaultVal(Class<?> clz) {
        if (clz == boolean.class) {
            return false;
        } else if (clz == byte.class) {
            return (byte) 0;
        } else if (clz == short.class) {
            return (short) 0;
        } else if (clz == int.class) {
            return 0;
        } else if (clz == long.class) {
            return 0L;
        } else if (clz == float.class) {
            return 0F;
        } else if (clz == double.class) {
            return 0D;
        } else if (clz == char.class) {
            return (char) 0;
        }
        return null;
    }


}
