package com.homework.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Stream<T> {
    private final ArrayList<T> list;

    public Stream(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    @SafeVarargs
    public static <T> Stream<T> of(T... values) {
        return new Stream<>(Arrays.asList(values));
    }

    public Stream<T> filter(Predicate<T> predicate) {
        list.removeIf(predicate.negate());
        return this;
    }

    public Stream<T> map(Function<T, T> mapper) {
        list.replaceAll(mapper::apply);
        return this;
    }

    public Stream<T> distinct() {
        HashSet<T> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        return this;
    }

    public void	forEach(Consumer<T> action) {
        list.forEach(action);
    }
}
