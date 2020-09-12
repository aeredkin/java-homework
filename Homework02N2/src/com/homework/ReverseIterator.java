package com.homework;

import java.util.Iterator;
import java.util.List;

public class ReverseIterator implements Iterator<String> {
    protected final List<String> list;
    protected int currentPosition;

    ReverseIterator(List<String> list) {
        this.list = list;
        this.currentPosition = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentPosition >= 0;
    }

    @Override
    public String next() {
        return list.get(currentPosition--);
    }
}
