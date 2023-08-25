package io.github.yaowenbin.commons.datetime;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TimesTest {

    @Test
    void str2Mils() {
        assertThat(Times.str2Mills("30s")).isEqualTo(1000L * 30);
        assertThat(Times.str2Mills("60m")).isEqualTo(1000L * 60 * 60);
        assertThat(Times.str2Mills("1h")).isEqualTo(1000L * 60 * 60);
        assertThat(Times.str2Mills("1d")).isEqualTo(1000L * 60 * 60 * 24);
        assertThat(Times.str2Mills("2w")).isEqualTo(1000L * 60 * 60 * 24 * 14);
        assertThat(Times.str2Mills("3M")).isEqualTo(1000L * 60 * 60 * 24 * 90);
        assertThat(Times.str2Mills("2y")).isEqualTo(1000L * 60 * 60 * 24 * 365 * 2);

        assertThat(Times.str2Mills("9999a")).isEqualTo(9999);
    }

}