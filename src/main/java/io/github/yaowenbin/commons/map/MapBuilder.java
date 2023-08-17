package io.github.yaowenbin.commons.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  
 *  
 */
public class MapBuilder<K, V> {

    public static final int INITIAL_SIZE = 16;

    private final Map<K, V> insideMap;

    public MapBuilder() {
        this(INITIAL_SIZE);
    }

    public MapBuilder(int initSize) {
        initSize = Math.max(initSize, 1);
        insideMap = new HashMap<>(initSize);
    }


    /**
     *
     * @param key key that want to store in map
     * @param value value that want to store in map
     * @return mapBuilder's Reference to chain call.
     */
    public MapBuilder<K, V> put(K key, V value) {
        insideMap.put(key, value);
        return this;
    }

    /**
     * build a new HashMap based on Builder's inside map.
     * if just return inside map.
     * 1. the repeat call will return the same reference
     * 2. GC cannot clean the inside map until caller is cleaned.
     * @return map contains all the Entry of after build() methods.
     */
    public Map<K, V> build() {
        return new HashMap<>(insideMap);
    }

    /**
     * build a new TreeMap based on Builder's inside map.
     * @return TreeMap
     */
    public Map<K, V> buildTreeMap() {
        return new TreeMap<>(insideMap);
    }

    /**
     * build a new TreeMap based on Builder's inside map.
     * @return TreeMap
     */
    public Map<K, V> buildLinkedMap() {
        return new LinkedHashMap<>(insideMap);
    }

    /**
     * build a new TreeMap based on Builder's inside map.
     * @return TreeMap
     */
    public Map<K, V> buildConcurrentMap() {
        return new ConcurrentHashMap<>(insideMap);
    }
}
