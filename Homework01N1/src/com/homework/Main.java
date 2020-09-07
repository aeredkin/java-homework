package com.homework;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите размер массива.");
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] array = new int[length];

        System.out.println("Введите элементы массива.");
        for (int i = 0; i < array.length; ++i) {
            array[i] = in.nextInt();
        }

        for (int i = 1; i < array.length; ++i) {
            for (int j = 0; j < array.length - i; ++j) {
                if (array[j] > array [j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        System.out.println("Отсортированный массив:");
        for (int item : array) {
            System.out.println(item);
        }
    }
}
