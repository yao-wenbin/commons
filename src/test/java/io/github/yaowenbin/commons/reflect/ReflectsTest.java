package io.github.yaowenbin.commons.reflect;

import io.github.yaowenbin.commons.UnitTest;
import io.github.yaowenbin.commons.datetime.Timer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectsTest extends UnitTest {

    @Test
    void setAccessible_shouldImprovePerforamnce_whenAccessibleIsTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SimpleBean simpleBean = new SimpleBean();
        Class<SimpleBean> clz = SimpleBean.class;
        Method getter = clz.getDeclaredMethod("getter");

        assertThat(getter.isAccessible()).isFalse();
        Timer timer = Timer.start();
        for (int i = 0; i < 2000; i++) {
            getter.invoke(simpleBean);
        }
        long inaccessibleInterval = timer.intervalAndRest();


        Reflects.setAccessible(getter);
        for (int i = 0; i < 2000; i++) {
            getter.invoke(simpleBean);
        }
        assertThat(timer.interval()).isLessThanOrEqualTo(inaccessibleInterval);
    }

}

class SimpleBean {

    void getter() {
        return;
    }

}