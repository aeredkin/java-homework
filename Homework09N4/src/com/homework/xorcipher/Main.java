package com.homework.xorcipher;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст, который нужно зашифровать.");
        String text = scanner.next();
        System.out.println("Введите гамму.");
        String gamma = scanner.next();
        encrypt(text, gamma);
    }

    public static void encrypt(String text, String gamma) {
        if (text.length() != gamma.length()) {
            System.out.println("Текст и гамма должны быть одинаковой длины.");
            return;
        }

        AtomicInteger position = new AtomicInteger();
        text.chars().map(c -> (c - 'a' + 1) + (gamma.charAt(position.getAndIncrement()) - 'a' + 1))
                .forEach(c -> System.out.print((char) ((c - 1) % ('z' - 'a' + 1) + 'a')));
    }
}
