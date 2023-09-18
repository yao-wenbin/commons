package io.github.yaowenbin.commons.map;


public class Pair<K, V> {

    private final K key;
    private final V val;

    public Pair(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K key() {
        return key;
    }

    public V val() {
        return val;
    }

}
