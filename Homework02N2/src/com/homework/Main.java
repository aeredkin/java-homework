package com.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        String s = System.getProperty("line.separator");
        String text = "4465 433" + s + "898 66 2321" + s + "4465 994345" + s + "433 5334";
        String[] words = text.split("\\s+");

        TreeMap<String, Integer> wordsAndCount = new TreeMap<>(new LengthComparator());
        for (String word : words) {
            Integer value = wordsAndCount.get(word);
            if (value == null) {
                wordsAndCount.put(word, 1);
            } else {
                wordsAndCount.put(word, ++value);
            }
        }

        System.out.println("Различных слов " + wordsAndCount.size());

        System.out.println(s + "Список различных слов, отсортированный по их длине");
        for (String word : wordsAndCount.keySet()) {
            System.out.println(word);
        }

        System.out.println(s + "Список различных слов с их количеством");
        for (Map.Entry<String, Integer> wordAndCount : wordsAndCount.entrySet()) {
            System.out.println(wordAndCount.getKey() + " " + wordAndCount.getValue());
        }

        System.out.println(s + "Строки в обратном порядке");
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(text.split(s)));
        ReverseIterator reverseIterator = new ReverseIterator(strings);
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }

        System.out.println(s + "Введите номера строк для вывода и любую букву для завершения ввода");
        ArrayList<Integer> stringNumbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            stringNumbers.add(scanner.nextInt());
        }
        for (int stringNumber : stringNumbers) {
            if (stringNumber < strings.size()) {
                System.out.println(strings.get(stringNumber));
            } else {
                System.out.println("Нет строки с номером " + stringNumber);
            }
        }
    }
}
