package com.homework.collectionutils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils<T> {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<? extends T> limit(List<? extends T> source, int size) {
        return source.subList(0, size - 1);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T o : c2) {
            if (c1.contains(o)) {
                return true;
            }
        }
        return false;
    }

    public static <T> List<? extends Comparable<T>> range(List<? extends Comparable<T>> list, T min, T max) {
        List<Comparable<T>> result = newArrayList();
        for (Comparable<T> element : list) {
            if (element.compareTo(min) >= 0 && element.compareTo(max) <= 0) {
                result.add(element);
            }
        }
        return result;
    }

    public static <T> List<? extends T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        list.sort(comparator);
        return list.subList(list.indexOf(min), list.indexOf(max));
    }
}