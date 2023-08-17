package io.github.yaowenbin.commons.list;

import java.util.ArrayList;
import java.util.Collection;
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


}
