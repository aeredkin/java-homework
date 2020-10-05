package com.homework.tribonacci;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println(generateTribonacci(20L));
    }

    public static List<Long> generateTribonacci(long n) {
        return Stream.iterate(new long[]{0, 0, 1}, a -> new long[]{a[1], a[2], a[0] + a[1] + a[2]})
                .limit(n).map(a -> a[0]).collect(Collectors.toList());
    }
}
