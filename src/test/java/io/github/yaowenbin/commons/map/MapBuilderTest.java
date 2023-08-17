package io.github.yaowenbin.commons.map;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author yaowenbin
 * @Date 2023/8/17
 */
class MapBuilderTest extends UnitTest {

    MapBuilder<String, Object> builder = new MapBuilder<String, Object>().put("key", "value");

    @Test
    void build() {
        Map<Object, Object> map = new MapBuilder<>().build();

        assertThat(map).isNotNull();
    }

    @Test
    void putAndBuild() {
        Map<String, String> map = new MapBuilder<String, String>()
                .put("key", "value")
                .put("key2", "value2")
                .build();

        assertThat(map.get("key")).isEqualTo("value");
        assertThat(map.get("key2")).isEqualTo("value2");
        assertThat(map.get("no-exists-key")).isEqualTo(null);
    }

    @Test
    void build_shouldReturnDiffReference_butSameEntry() {
        MapBuilder<String, Object> builder = new MapBuilder<String, Object>()
                .put("key", "value");

        Map<String, Object> map1 =
                builder.build();
        Map<String, Object> map2 =
                builder.build();

        assertThat(map1).isNotSameAs(map2);
        assertThat(map1.get("key")).isEqualTo(map2.get("key"));
    }

    @Test
    void build_differentMapType() {
        Map<String, Object> treeMap = builder.buildTreeMap();
        assertThat(treeMap).isInstanceOf(TreeMap.class);
        assertThat(treeMap.get("key")).isEqualTo("value");

        Map<String, Object> linkedMap = builder.buildLinkedMap();
        assertThat(linkedMap).isInstanceOf(LinkedHashMap.class);
        assertThat(linkedMap.get("key")).isEqualTo("value");

        Map<String, Object> concurrentMap = builder.buildConcurrentMap();
        assertThat(concurrentMap).isInstanceOf(ConcurrentHashMap.class);
        assertThat(concurrentMap.get("key")).isEqualTo("value");
    }
}