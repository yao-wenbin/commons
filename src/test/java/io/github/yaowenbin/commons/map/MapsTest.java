package io.github.yaowenbin.commons.map;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MapsTest extends UnitTest {

    @Test
    void builderAndBuild() {
        Map<String, Object> map = Maps.<String, Object>builder()
                .put("123", "123")
                .put("a", "bc").build();

        assertThat(map).isInstanceOf(HashMap.class);
        assertThat(map).hasSize(2);
        assertThat(map.get("123")).isEqualTo("123");
    }

    @Test
    void builderWithInitialSize() {
        Map<String, Object> map = Maps.<String, Object>builder(2)
                .put("123", "123")
                .put("a", "bc").build();

        assertThat(map).isInstanceOf(HashMap.class);
        assertThat(map).hasSize(2);
        assertThat(map.get("123")).isEqualTo("123");
    }

}
