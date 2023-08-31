package io.github.yaowenbin.commons.bean;

import io.github.yaowenbin.commons.asserts.Asserts;
import io.github.yaowenbin.commons.reflect.Reflects;
import io.github.yaowenbin.commons.string.Strings;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * a
 */
public class FieldInfo {

    final Field field;
    Method getter;
    Method setter;

    public FieldInfo(Field field, Method getter, Method setter) {
        Asserts.notNull(field, "field should not be null");
        this.field = field;
        this.getter = getter;
        this.setter = setter;
    }

    public static FieldInfo buildBy(Field field, Method[] methods) {
        Method getter = null, setter = null;
        for (Method method : methods) {
            // getter should have zero parameter count.
            if (getter == null && method.getParameterCount() == 0
            && matchGetter(field, method)) {
                getter = method;

            }
            if (setter == null && method.getParameterCount() == 1
                    && matchSetter(field, method)) {
                setter = method;
            }

            if (getter != null && setter != null)
                break;
        }
        return new FieldInfo(field, getter, setter);
    }

    private static boolean matchSetter(Field field, Method method) {
        String fieldName = field.getName();
        String methodName = method.getName();

        return matchSetterDefaultStyle(fieldName, methodName) || matchFluentStyle(fieldName, methodName);
    }

    private static boolean matchSetterDefaultStyle(String fieldName, String methodName) {
        String matchedFieldName = Strings.upperFirstChar(fieldName);
        return methodName.equals("set" + matchedFieldName);
    }

    private static boolean matchGetter(Field field, Method method) {
        String fieldName = field.getName();
        String methodName = method.getName();

        return matchGetterDefaultStyle(fieldName, methodName) || matchFluentStyle(fieldName, methodName);
    }

    private static boolean matchGetterDefaultStyle(String fieldName, String methodName) {
        String matchedFieldName = Strings.upperFirstChar(fieldName);
        return  methodName.equals("get" + matchedFieldName);
    }

    private static boolean matchFluentStyle(String fieldName, String methodName) {
        return methodName.equals(fieldName);
    }

    public String name() {
        return field.getName();
    }

    public boolean isReadable() {
        return getter != null && Reflects.isPublic(getter);
    }

    public boolean unReadable() {
        return !isReadable();
    }

    public boolean isWriteable() {
        return setter != null && Reflects.isPublic(setter);
    }

    public boolean unWritable() {
        return !isWriteable();
    }

    public Object getValue(Object obj) {
        if (getter == null)
            return null;
        return Reflects.invoke(obj, getter);
    }

    public void setValue(Object obj, Object value) {
        if (setter != null) {
            Reflects.invoke(obj, setter, value);
        }
    }
}

