package io.github.yaowenbin.commons.map;


public class Maps {

    private Maps() {}

    public static <K, V> MapBuilder<K, V> builder(int size) {
        return new MapBuilder<>(size);
    }

    public static <K, V> MapBuilder<K, V> builder() {
        return new MapBuilder<>();
    }
}
