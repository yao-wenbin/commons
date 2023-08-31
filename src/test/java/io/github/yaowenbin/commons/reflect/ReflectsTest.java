package io.github.yaowenbin.commons.reflect;

import io.github.yaowenbin.commons.UnitTest;
import io.github.yaowenbin.commons.datetime.Timer;
import io.github.yaowenbin.commons.list.Lists;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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

    @Test
    void getMethods() {
        List<Object> res = Lists.builder().add("publicGetter").add("subGetter").build();

        Method[] methods = Reflects.getPublicMethods(SubBean.class);

        assertThat(methods).allMatch(method -> res.contains(method.getName()));
    }

    @Test
    void getMethodsWithSuppers() {
        List<Object> res = Lists.builder().add("publicGetter").add("subGetter").build();

        Method[] methods = Reflects.getPublicMethods(SubBean.class, true);

        // check same name method will only return one.
        assertThat(methods).hasSize(2);
        assertThat(methods).allMatch(method -> res.contains(method.getName()));
    }

    @Test
    void fields() {
        Field[] fields = Reflects.fields(SubBean.class);
        assertThat(fields).hasSize(7);
    }

    @Test
    void invoke_getter() throws NoSuchMethodException {
        SimpleBean bean = new SimpleBean();
        bean.publicField = "foo";
        Method publicGetter = SimpleBean.class.getDeclaredMethod("publicGetter");

        Object result = Reflects.invoke(bean, publicGetter);

        assertThat(result).isEqualTo("foo");
    }

    @Test
    void invoke_setter() throws NoSuchMethodException {
        SimpleBean bean = new SimpleBean();

        Method setter = SimpleBean.class.getDeclaredMethod("publicSetter", String.class);
        Reflects.invoke(bean, setter, "bar");

        assertThat(bean.publicField).isEqualTo("bar");
    }

    @Test
    void newInstance() {
        SimpleBean res = Reflects.newInstance(SimpleBean.class);
        assertThat(res).isNotNull();

        OneConstructorBean oneConstructorBean = Reflects.newInstance(OneConstructorBean.class);
        assertThat(oneConstructorBean).isNotNull();
    }

}

class SimpleBean {

    public String publicField;
    protected String protectedField;
    private String privateField;
    String defaultField;

    void getter() {
    }

    public String publicGetter() {
        return publicField;
    }

    private String privateGetter() {
        return privateField;
    }

    protected String protectedGetter() {
        return publicField;
    }

    public void publicSetter(String publicField) {
        this.publicField = publicField;
    }

}

class OneConstructorBean {

    private String field;

    public OneConstructorBean(String field) {
        this.field = field;
    }
}

class SubBean extends SimpleBean {
    // to mock test case class and supper class have same name field.
    public String publicField;
    protected String subProtectedField;
    private String subPrivateField;
    public void subGetter() {}
    public String publicGetter() {
        return publicField;
    }
}