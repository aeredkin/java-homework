package com.homework;

import java.util.Comparator;

public class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        int result = s1.length() - s2.length();
        if (result == 0) {
            return s1.compareTo(s2);
        } else {
            return result;
        }
    }
}
