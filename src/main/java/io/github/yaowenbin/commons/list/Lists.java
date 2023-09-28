package io.github.yaowenbin.commons.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lists {

    private Lists() {}

    public static <T, R> List<R> map(Collection<T> collection, Function<T, R> mappingFunction) {
        return collection.stream()
                .filter(Objects::nonNull)
                .map(mappingFunction)
                .collect(Collectors.toList());
    }

    public static <T, R> List<R> mapDistinct(Collection<T> collection, Function<T, R> mappingFunction) {
        return collection.stream().map(mappingFunction).distinct()
                .collect(Collectors.toList());
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> List<T> newLinkedList() {
        return new LinkedList<>();
    }

    public static <T> List<T> newCopyOnWriteList() {
        return new CopyOnWriteArrayList<>();
    }


    public static <T> ListBuilder<T> builder() {
        return new ListBuilder<>();
    }


    /**
     * add multi element into a ArrayList.
     * @param elements single or multi object.
     * @return ArrayList containers all elements.
     * @param <T> element type.
     */
    public static <T> List<T> of(T... elements) {
        return Arrays.stream(elements).collect(Collectors.toList());
    }

    /**
     * add multi element into a ArrayList.
     * @param elements single or multi object.
     * @return ArrayList containers all elements.
     * @param <T> element type.
     */
    public static <T> List<T> unmodifiedOf(T... elements) {
        return Collections.unmodifiableList(of(elements));
    }

    public static <T> List<T> unmodifiedOf(List<T> elementList) {
        return Collections.unmodifiableList(elementList);
    }

    /**
     * @return unmodified empty List
     * @param <T> list generic type.
     */
    public static <T> List<T> empty() {
        return Collections.emptyList();
    }
}
