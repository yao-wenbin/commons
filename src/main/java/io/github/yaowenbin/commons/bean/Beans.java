package io.github.yaowenbin.commons.bean;

import io.github.yaowenbin.commons.asserts.Asserts;
import io.github.yaowenbin.commons.reflect.Reflects;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yaowenbin
 * @Date 2023/8/28
 */
public class Beans {


    public static <T> T fromMap(Map<String, Object> fromMap, Class<T> clz) {
        return null;
    }

    /**
     * to align the common BeanUtil#copyProperties.
     * @param <T> BeanType
     */
    public static <T, S> T copyProperties(S source, T target) {
        Asserts.notNull(source, "source should not be null");
        Asserts.notNull(target, "target should not be null");
        Class<?> sourceClz = source.getClass();
        BeanInfo sourceBeanInfo = CachedBeanInfo.getBeanInfo(sourceClz);
        Map<String, FieldInfo> sourceFieldMap = sourceBeanInfo.fieldMap();

        Class<?> targetClz = target.getClass();
        BeanInfo tBeanInfo = CachedBeanInfo.getBeanInfo(targetClz);
        Map<String, FieldInfo> targetFieldMap = tBeanInfo.fieldMap();


        sourceFieldMap.forEach((sFieldName, sFieldInfo) -> {
            if (sFieldInfo.unReadable())
                return;

            FieldInfo tFieldInfo = targetFieldMap.get(sFieldName);
            if (tFieldInfo == null || tFieldInfo.unWritable())
                return;

            Object sourceValue = sFieldInfo.getValue(source);
            tFieldInfo.setValue(target, sourceValue);
        });
        return target;
    }


    public static <T, S> T copyProperties(S source, Class<T> targetClass) {
        T target = Reflects.newInstance(targetClass);
        return copyProperties(source, target);
    }

    public static <T> Map<String, Object> toMap(T source) {
        Class<?> sourceClz = source.getClass();

        BeanInfo beanInfo = CachedBeanInfo.getBeanInfo(sourceClz);
        Map<String, FieldInfo> fieldInfoMap = beanInfo.readableFieldMap();

        Map<String, Object> res = new HashMap<>(fieldInfoMap.size());
        fieldInfoMap.forEach((fieldName, fieldInfo) -> {
            Object val = fieldInfo.getValue(source);
            res.put(fieldName, val);
        });

        return res;
    }
}
