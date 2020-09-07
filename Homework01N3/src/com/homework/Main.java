package com.homework;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Введите температуру в градусах Цельсия.");
        Scanner scanner = new Scanner(System.in);
        int celsius = scanner.nextInt();

        ToFahrenheitConverter toFahrenheitConverter = new ToFahrenheitConverter();
        System.out.println("Это " + toFahrenheitConverter.convert(celsius) + " градусов Фаренгейта.");
        ToRankineConverter toRankineConverter = new ToRankineConverter();
        System.out.println("Это " + toRankineConverter.convert(celsius) + " градусов Ранкина.");
    }
}
