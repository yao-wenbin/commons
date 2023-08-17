package io.github.yaowenbin.commons.obj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *  
 *  
 */
public class ObjsTest {
    Person person;

    @BeforeEach
    public void setup() {
        person = new Person();
    }

    @Test
    public void getOrDefault_returnVal_whenValNotNull() {
        person.id(3L);

        Object result = Objs.getOrDefault(person.id(), 2L);

        assertEquals(3L, result);
    }

    @Test
    public void getOrDefault_returnVal_whenValIsNull() {

        Object result = Objs.getOrDefault(person.id(), 2L);

        assertEquals(2L, result);
    }

    @Test
    public void getOrDefaultString_returnVal_whenValIsNull() {
        person.username("");

        Object result = Objs.getOrDefault(person.username(), "yaowb");

        assertEquals("yaowb", result);
    }

    @Test
    public void testGetOrDefault2() {
        person = Mockito.mock(Person.class);
        person.id(1L);

        Object result = Objs.getOrDefault(person.id(), () -> person.no());

        verify(person, times(0)).no();
    }
}


class Person {
    private Long id;
    private Long no;
    private String username;

    public Long id() {
        return id;
    }

    public void id(Long id) {
        this.id = id;
    }

    public Long no() {
        return no;
    }

    public void no(Long no) {
        this.no = no;
    }

    public String username() {
        return username;
    }

    public void username(String username) {
        this.username = username;
    }
}

