package io.github.yaowenbin.commons.asserts;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssertsTest extends UnitTest {

    @Test
    public void assertNotNull_shouldThrowNPE_whenObjectIsNull() {
        NullPointerException npe = assertThrows(NullPointerException.class, () -> {
            Asserts.notNull(null, "not null Ok?");
        });

        assertThat(npe).hasMessage("not null Ok?");
    }

    @Test
    public void assertNotNull_shouldDoNothing_whenObjectIsNotNUll() {
        assertDoesNotThrow(() -> {
            Asserts.notNull(new Long(1L), "not null");
        });
    }

}