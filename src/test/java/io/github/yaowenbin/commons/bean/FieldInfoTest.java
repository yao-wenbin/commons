package io.github.yaowenbin.commons.bean;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class FieldInfoTest extends UnitTest {

    @Test
    void construction_shouldThrowNpe_whenFieldIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new FieldInfo(null, null, null);
        });
    }

    @Test
    void isReadable_shouldReturnFalse_whenFieldGetterIsNull() throws NoSuchFieldException, NoSuchMethodException {
        Field field1 = FieldInfoTestBean.class.getDeclaredField("field1");
        FieldInfo fd = new FieldInfo(field1, null, null);
        assertThat(fd.isReadable()).isFalse();

        Field field2 = FieldInfoTestBean.class.getDeclaredField("field2");
        Method getter = FieldInfoTestBean.class.getDeclaredMethod("getField2");
        fd = new FieldInfo(field2, getter, null);
        assertThat(fd.isReadable()).isFalse();
    }

    @Test
    void isReadable_shouldTrue_whenGetterIsPublic() throws NoSuchMethodException, NoSuchFieldException {
        Field id = FieldInfoTestBean.class.getDeclaredField("field1");
        Method getter = FieldInfoTestBean.class.getDeclaredMethod("getField1");

        FieldInfo result = new FieldInfo(id, getter, null);

        assertThat(result.isReadable()).isTrue();
    }

    @Test
    void isReadable_shouldFalse_whenGetterIsNonPublic() throws NoSuchMethodException, NoSuchFieldException {
        Field id = FieldInfoTestBean.class.getDeclaredField("field2");
        Method getter = FieldInfoTestBean.class.getDeclaredMethod("getField2");

        FieldInfo result = new FieldInfo(id, getter, null);

        assertThat(result.isReadable()).isFalse();
    }

    @Test
    void isWriteable_shouldTrue_whenSetterIsPublic() throws NoSuchFieldException, NoSuchMethodException {
        Field id = FieldInfoTestBean.class.getDeclaredField("field1");
        Method setter = FieldInfoTestBean.class.getDeclaredMethod("setField1", String.class);

        FieldInfo result = new FieldInfo(id, null, setter);

        assertThat(result.isWriteable()).isTrue();
    }

    @Test
    void isWriteable_shouldFalse_whenSetterIsNotPublic() throws NoSuchFieldException, NoSuchMethodException {
        Field id = FieldInfoTestBean.class.getDeclaredField("field2");
        Method setter = FieldInfoTestBean.class.getDeclaredMethod("setField2", Long.class);

        FieldInfo result = new FieldInfo(id, null, setter);

        assertThat(result.isWriteable()).isFalse();
    }

}

class FieldInfoTestBean {

    private String field1;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    // unreadable field.
    private Long field2;

    private Long getField2() {
        return field2;
    }

    private void setField2(Long field2) {
        this.field2 = field2;
    }
}

