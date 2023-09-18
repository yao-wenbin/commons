package io.github.yaowenbin.commons.map;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Test;

class PairTest extends UnitTest {

    @Test
    void key_val() {
        String key = "key";
        Long val = 30L;

        Pair<String, Long> pair = new Pair(key, val);

        assertThat(pair.key()).isEqualTo("key");
        assertThat(pair.val()).isEqualTo(30L);
    }

}