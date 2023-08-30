package io.github.yaowenbin.commons.datetime;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Test;

/**
 * @Author yaowenbin
 * @Date 2023/8/28
 */
class TimerTest extends UnitTest {

    @Test
    void interval() throws InterruptedException {
        Timer timer = Timer.start();
        Thread.sleep(2000);
        long interval = timer.interval();
        // to get approximate value
        assertThat(interval / 1000L * 1000).isEqualTo(2000L);
    }

    @Test
    void intervalSeconds() throws InterruptedException {
        Timer timer = Timer.start();
        Thread.sleep(3000);
        // to get approximate value
        long interval = timer.intervalSecond();
        assertThat(interval).isEqualTo(3);
    }

    @Test
    void intervalMinutes() throws InterruptedException {
        Timer timer = Timer.start();
        Thread.sleep(2000);
        // to get approximate value
        long interval = timer.intervalMinutes();
        assertThat(interval).isEqualTo(0);
    }

    @Test
    void intervalAndReset() throws InterruptedException {
        Timer timer = Timer.start();
        Thread.sleep(2000);
        long interval = timer.intervalAndRest();
        assertThat(interval / 1000L * 1000).isEqualTo(2000L);

        long restInterval = timer.interval();
        assertThat(restInterval / 1000L * 1000).isEqualTo(0L);
    }

}