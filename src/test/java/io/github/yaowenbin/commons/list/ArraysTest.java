package io.github.yaowenbin.commons.list;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Test;


/**
 * @Author yaowenbin
 * @Date 2023/8/25
 */
class ArraysTest extends UnitTest {

    @Test
    void isEmpty() {
        boolean result = Arrays.isEmpty(null);
        assertThat(result).isTrue();

        result = Arrays.isEmpty(new String[]{});
        assertThat(result).isTrue();

        result = Arrays.isEmpty(new Object[]{});
        assertThat(result).isTrue();

        result = Arrays.isEmpty(new Integer[]{1, 2, 3});
        assertThat(result).isFalse();
    }

}