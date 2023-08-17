package io.github.yaowenbin.commons.string;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @Author yaowenbin
 * @Date 2023/8/17
 */
class StringsTest extends UnitTest {

    @Test
    void isEmpty() {
        String str = null;
        assertThat(Strings.isEmpty(str)).isTrue();

        str = "";
        assertThat(Strings.isEmpty(str)).isTrue();

        str = "String";
        assertThat(Strings.isEmpty(str)).isFalse();
    }

    @Test
    void notEmpty() {
        String str = null;
        assertThat(Strings.notEmpty(str)).isFalse();

        str = "";
        assertThat(Strings.notEmpty(str)).isFalse();

        str = "String";
        assertThat(Strings.notEmpty(str)).isTrue();
    }


    @Test
    void isBlank() {
        String str = null;
        assertThat(Strings.isBlank(str)).isTrue();

        str = "";
        assertThat(Strings.isBlank(str)).isTrue();

        str = "    ";
        assertThat(Strings.isBlank(str)).isTrue();

        str = "String";
        assertThat(Strings.isBlank(str)).isFalse();
    }

    @Test
    void notBlank() {
        String str = null;
        assertThat(Strings.notBlank(str)).isFalse();

        str = "";
        assertThat(Strings.notBlank(str)).isFalse();

        str = "    ";
        assertThat(Strings.notBlank(str)).isFalse();

        str = "String";
        assertThat(Strings.notBlank(str)).isTrue();
    }

    @Test
    @Disabled
    void format() {
        String str = "you are {}";
        String param1 = "pig";

        assertThat(Strings.format(str, param1)).isEqualTo("you are pig");
    }
}