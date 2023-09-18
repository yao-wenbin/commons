package io.github.yaowenbin.commons.concurrency;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class SchedulerTest extends UnitTest {

    TestScheduler testScheduler = Mockito.spy(TestScheduler.class);

    @BeforeEach
    void setUp() {
        testScheduler.start();
    }

    @Test
    void start() throws InterruptedException {
        Thread.sleep(2000);
        assertThat(testScheduler.started()).isTrue();
        verify(testScheduler, atLeastOnce()).doSomething();
    }

    @Test
    void stop() throws InterruptedException {
        testScheduler.shutdown();

        Thread.sleep(2000);
        assertThat(testScheduler.started()).isFalse();
        verify(testScheduler, atMostOnce()).doSomething();
    }

}

class TestScheduler extends Scheduler {

    @Override
    public void doSomething() {
        System.out.println("dosomething");
    }

    @Override
    public long getInterval() {
        return 1000;
    }
}