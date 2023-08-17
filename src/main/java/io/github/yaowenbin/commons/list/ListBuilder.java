package io.github.yaowenbin.commons.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author yaowenbin
 * @Date 2023/8/17
 */
public class ListBuilder<T> {

    public static final int INITIAL_SIZE = 16;

    private final List<T> insideList;

    public ListBuilder() {
        this(INITIAL_SIZE);
    }

    public ListBuilder(int initialSize) {
        insideList = new ArrayList<>(initialSize);
    }

    public ListBuilder<T> add(T element) {
        insideList.add(element);
        return this;
    }

    public List<T> build() {
        return new ArrayList<>(insideList);
    }


    public List<T> buildLinkedList() {
        return new LinkedList<>(insideList);
    }

    public List<T> buildCopyOnWriteList() {
        return new CopyOnWriteArrayList<>(insideList);
    }
}
