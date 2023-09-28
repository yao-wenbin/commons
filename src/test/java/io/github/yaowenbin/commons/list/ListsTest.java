package io.github.yaowenbin.commons.list;

import io.github.yaowenbin.commons.Person;
import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListsTest extends UnitTest {

    @Test
    void newArrayList() {
        assertThat(Lists.newArrayList()).isInstanceOf(ArrayList.class);
    }

    @Test
    void newLinkedList() {
        assertThat(Lists.newLinkedList()).isInstanceOf(LinkedList.class);
    }

    @Test
    void newCopyOnWriteList() {
        assertThat(Lists.newCopyOnWriteList()).isInstanceOf(CopyOnWriteArrayList.class);
    }

    @Test
    void builder_shouldReturnDifferInstanceType_whenCallDifferBuildAPI() {
        List<Object> list = Lists.builder().build();
        assertThat(list).isInstanceOf(ArrayList.class);

        List<String> stringLinkedList = Lists.<String>builder().add("duck").buildLinkedList();
        assertThat(stringLinkedList).isInstanceOf(LinkedList.class);
        assertThat(stringLinkedList).element(0).isEqualTo("duck");

        List<Double> doubleCopyOnWriteList = Lists.<Double>builder().add(9999D).buildCopyOnWriteList();
        assertThat(doubleCopyOnWriteList).isInstanceOf(CopyOnWriteArrayList.class);
        assertThat(doubleCopyOnWriteList).element(0).isEqualTo(9999D);
    }

    @Test
    void builder_shouldReturnDifferReference_whenRepeatCallSameAPI() {
        ListBuilder<Object> builder = Lists.builder().add("foo");

        List<Object> list1 = builder.build();
        List<Object> list2 = builder.build();

        assertThat(list1).isNotSameAs(list2);
        assertThat(list1).element(0).isEqualTo(list2.get(0));
    }

    @Test
    void builder_shouldReturnSameValue_whenRepeatCallBuildAPI() {
        ListBuilder<Object> builder = Lists.builder().add("foo");

        List<Object> list1 = builder.buildLinkedList();
        List<Object> list2 = builder.buildCopyOnWriteList();

        assertThat(list1).isNotSameAs(list2);
        assertThat(list1).element(0).isEqualTo(list2.get(0));
    }

    @Test
    void map() {
        List<Person> personList = Lists.<Person>builder()
                .add(new Person(1, 9999L, "foo"))
                .add(new Person(2, 8848L, "bar"))
                .build();

        List<Integer> idList = Lists.map(personList, Person::id);


        assertThat(idList).element(0).isEqualTo(1);
        assertThat(idList).element(1).isEqualTo(2);
    }

    @Test
    void map_shouldFilterNullVal () {
        List<Object> list = Lists.builder().add(null).add(null).build();

        list = Lists.map(list, Object::toString);

        assertThat(list).hasSize(0);
    }

    @Test
    void mapDistinct() {
        List<Person> personList = Lists.<Person>builder()
                .add(new Person(1, 9999L, "foo"))
                .add(new Person(1, 8848L, "bar"))
                .build();

        List<Integer> idList = Lists.mapDistinct(personList, Person::id);


        assertThat(idList).hasSize(1);
        assertThat(idList).element(0).isEqualTo(1);
    }

    @Test
    void of () {
        Person person = new Person(1, 9999L, "foo");
        Person person2 = new Person(2, 10000L, "bar");

        List<Person> result = Lists.of(person, person2);
        assertThat(result).hasSize(2);
        assertThat(result).isInstanceOf(ArrayList.class);

        assertDoesNotThrow(() -> {
            result.add(new Person());
        });
    }

    @Test
    void of_CollectionParam() {

    }

    @Test
    void unmodifiedOf() {
        Person person = new Person(1, 9999L, "foo");
        Person person2 = new Person(2, 10000L, "bar");

        List<Person> unmodified = Lists.unmodifiedOf(person, person2);
        assertThat(unmodified).hasSize(2);

        assertThrows(UnsupportedOperationException.class, () -> {
            unmodified.add(new Person());
        });
    }

    @Test
    void unmodifiedOf_listTypeParam() {
        List<Person> persons = Lists.of(new Person(1, 9999L, "foo"), new Person(2, 10000L, "bar"));

        List<Person> unmodified = Lists.unmodifiedOf(persons);
        assertThat(unmodified).hasSize(2);

        assertThrows(UnsupportedOperationException.class, () -> {
            unmodified.add(new Person());
        });
    }

    @Test
    void empty() {
        List<Object> empty = Lists.empty();

        assertThat(empty).hasSize(0);
        assertThrows(UnsupportedOperationException.class, () -> {
            empty.add(new Person());
        });
    }

}