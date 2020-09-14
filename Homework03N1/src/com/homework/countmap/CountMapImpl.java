package com.homework.countmap;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    protected final HashMap<T, Integer> map = new HashMap<>();

    @Override
    public void add(T o) {
        Integer value = map.get(o);
        if (value == null) {
            map.put(o, 1);
        } else {
            map.put(o, ++value);
        }
    }

    @Override
    public int getCount(T o) {
        return map.get(o);
    }

    @Override
    public int remove(T o) {
        return map.remove(o);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        for (Map.Entry<T, Integer> entry : source.toMap().entrySet()) {
            map.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(map);
    }
}
