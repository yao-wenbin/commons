package io.github.yaowenbin.commons.list;

import io.github.yaowenbin.commons.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class ListBuilderTest extends UnitTest {

    ListBuilder<String> builder = new ListBuilder<>();

    @Test
    void build() {
        List<String> list = builder.build();

        assertThat(list).isNotNull();
        assertThat(list).hasSize(0);
    }

    @Test
    void add() {
        List<String> list = builder
                .add("duck")
                .build();

        assertThat(list).hasSize(1);
        assertThat(list).element(0).isEqualTo("duck");
    }

    @Test
    void buildLinkedList() {
        assertThat(builder.buildLinkedList()).isInstanceOf(LinkedList.class);
    }

    @Test
    void buildCopyOnWriteList() {
        assertThat(builder.buildCopyOnWriteList()).isInstanceOf(CopyOnWriteArrayList.class);
    }

}