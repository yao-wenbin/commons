package io.github.yaowenbin.commons.bean;

import io.github.yaowenbin.commons.obj.Objs;
import io.github.yaowenbin.commons.reflect.Reflects;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * a
 */
public class FieldDescription {

    final Field field;
    Method getter;
    Method setter;

    public FieldDescription(Field field, Method getter, Method setter) {
        Objs.assertNotNull(field, "field should not be null");
        this.field = field;
        this.getter = getter;
        this.setter = setter;
    }

    public boolean isReadable() {
        return getter != null && Reflects.isPublic(getter);
    }

    public boolean isWriteable() {
        return setter != null && Reflects.isPublic(setter);
    }
}

