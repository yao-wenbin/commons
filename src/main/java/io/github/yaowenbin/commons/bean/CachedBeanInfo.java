package io.github.yaowenbin.commons.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachedBeanInfo {

    private final static  Map<Class<?>, BeanInfo> cachedBeanMap = new ConcurrentHashMap<>();

    public static BeanInfo getBeanInfo(Class<?> clz) {
        return cachedBeanMap.computeIfAbsent(clz, BeanInfo::new);
    }

}
