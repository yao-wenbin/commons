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

    @Test
    void isPublic_shouldReturnTrue_whenMethodIsPublic() throws NoSuchMethodException {
        Method getter = SimpleBean.class.getDeclaredMethod("getter");
        assertThat(Reflects.isPublic(getter)).isFalse();

        Method privateGetter = SimpleBean.class.getDeclaredMethod("privateGetter");
        assertThat(Reflects.isPublic(privateGetter)).isFalse();

        Method protectedGetter = SimpleBean.class.getDeclaredMethod("protectedGetter");
        assertThat(Reflects.isPublic(protectedGetter)).isFalse();

        Method publicGetter = SimpleBean.class.getDeclaredMethod("publicGetter");
        assertThat(Reflects.isPublic(publicGetter)).isTrue();
    }

}

class SimpleBean {

    void getter() {
    }

    public void publicGetter() {

    }

    private void privateGetter() {

    }

    protected void protectedGetter() {

    }

}