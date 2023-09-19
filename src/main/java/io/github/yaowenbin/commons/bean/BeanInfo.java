package io.github.yaowenbin.commons.bean;

import io.github.yaowenbin.commons.asserts.Asserts;
import io.github.yaowenbin.commons.reflect.Reflects;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanInfo {

    private final Class<?> clz;

    private final Map<String, FieldInfo> fieldMap;

    public BeanInfo(final Class<?> clz) {
        Asserts.notNull(clz);
        this.clz = clz;
        this.fieldMap = buildFields(clz);
    }

    private Map<String, FieldInfo> buildFields(Class<?> clz) {
        HashMap<String, FieldInfo> res = new HashMap<>();
        Method[] methods = Reflects.getPublicMethods(clz);

        Field[] fields = Reflects.fields(clz);
        for (Field field : fields) {
            FieldInfo fieldInfo = FieldInfo.buildBy(field, methods);
            res.putIfAbsent(fieldInfo.name(), fieldInfo);
        }
        return res;
    }

    public Map<String, FieldInfo> fieldMap() {
        return fieldMap;
    }

    public Map<String, FieldInfo> readableFieldMap() {
        Map<String, FieldInfo> res = new HashMap<>(fieldMap.size());
        fieldMap.entrySet().stream().filter(entry -> entry.getValue().isReadable()).forEach(entry -> {
            res.put(entry.getKey(), entry.getValue());
        });
        return res;
    }

    public FieldInfo getField(String fieldName) {
        return fieldMap.get(fieldName);
    }


}
