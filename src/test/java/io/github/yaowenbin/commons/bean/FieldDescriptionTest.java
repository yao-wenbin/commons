package io.github.yaowenbin.commons.bean;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class FieldDescriptionTest extends UnitTest {

    @Test
    void construction_shouldThrowNpe_whenFieldIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new FieldDescription(null, null, null);
        });
    }

    @Test
    void isReadable_shouldReturnFalse_whenFieldGetterIsNull() throws NoSuchFieldException, NoSuchMethodException {
        Field field1 = Bean.class.getDeclaredField("field1");
        FieldDescription fd = new FieldDescription(field1, null, null);
        assertThat(fd.isReadable()).isFalse();

        Method getter = Bean.class.getDeclaredMethod("getField1");
        fd = new FieldDescription(null, getter, null);
        assertThat(fd.isReadable()).isFalse();
    }

    @Test
    void isReadable_shouldTrue_whenGetterIsPublic() throws NoSuchMethodException, NoSuchFieldException {
        Field id = Bean.class.getDeclaredField("field1");
        Method getter = Bean.class.getDeclaredMethod("getField1");

        FieldDescription result = new FieldDescription(id, getter, null);

        assertThat(result.isReadable()).isTrue();
    }

    @Test
    void isReadable_shouldFalse_whenGetterIsNonPublic() throws NoSuchMethodException, NoSuchFieldException {
        Field id = Bean.class.getDeclaredField("field2");
        Method getter = Bean.class.getDeclaredMethod("getField2");

        FieldDescription result = new FieldDescription(id, getter, null);

        assertThat(result.isReadable()).isFalse();
    }

    @Test
    void isWriteable_shouldTrue_whenSetterIsPublic() throws NoSuchFieldException, NoSuchMethodException {
        Field id = Bean.class.getDeclaredField("field1");
        Method setter = Bean.class.getDeclaredMethod("setField1", String.class);

        FieldDescription result = new FieldDescription(id, null, setter);

        assertThat(result.isWriteable()).isTrue();
    }

    @Test
    void isWriteable_shouldFalse_whenSetterIsNotPublic() throws NoSuchFieldException, NoSuchMethodException {
        Field id = Bean.class.getDeclaredField("field2");
        Method setter = Bean.class.getDeclaredMethod("setField2", Long.class);

        FieldDescription result = new FieldDescription(id, null, setter);

        assertThat(result.isWriteable()).isFalse();
    }

}

class Bean {

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

