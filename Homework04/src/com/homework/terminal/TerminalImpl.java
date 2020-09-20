package com.homework.terminal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TerminalImpl {
    private final TerminalServer server = new TerminalServer();
    private final PinValidator pinValidator = new PinValidator();

    public void start() {
        ArrayList<Integer> numbers = new ArrayList<>();
        System.out.println("Введите ПИН-код по одной цифре на строке.");
        boolean operate = true;
        boolean block = false;
        Scanner scanner = new Scanner(System.in);
        Date blockTime = new Date();
        int attemptsNumber = 3;
        while (operate) {
            while (numbers.size() < 4) {
                try {
                    if (scanner.hasNextInt()) {
                        int number = scanner.nextInt();
                        block = deblock(block, blockTime);
                        if (number > -1 && number < 10) {
                            numbers.add(number);
                        } else {
                            System.out.println("Введите оставшиеся " + (4 - numbers.size()) +
                                    " цифры ПИН-кода по одной цифре на строке.");
                        }
                    } else {
                        scanner.next();
                        block = deblock(block, blockTime);
                        System.out.println("ПИН-код может содержать только цифры. Введите оставшиеся " +
                                (4 - numbers.size()) + " цифры ПИН-кода по одной цифре на строке.");
                    }
                } catch (AccountIsLockedException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (pinValidator.validate(numbers.get(0) * 1000 + numbers.get(1) * 100 + numbers.get(2) * 10 +
                    numbers.get(3))) {
                System.out.println("Доступно для снятия " + server.getFund() + ".");
                System.out.println("Введите положительную сумму, чтобы положить деньги или отрицательную, чтобы снять.");
                System.out.println("Сумма должна быть кратна 100. Для выхода введите 0.");
                while (operate) {
                    if (scanner.hasNextInt()) {
                        try {
                            server.changeAmount(scanner.nextInt());
                            System.out.println("Операция выполнена. На счёте " + server.getFund() + ".");
                            operate = false;
                        } catch (InsufficientFundsException | IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Повторите ввод.");
                        }
                    } else {
                        scanner.next();
                        System.out.println("Ввести нужно только число.");
                    }
                }
            } else {
                System.out.println("Неправильный ПИН-код.");
                --attemptsNumber;
                numbers.clear();
                if (attemptsNumber == 0) {
                    block = true;
                    blockTime = new Date();
                    System.out.println("Повторите ввод через 10 секунд.");
                    attemptsNumber = 3;
                } else {
                    System.out.println("Повторите ввод.");
                }
            }
        }
    }

    private boolean deblock(boolean block, Date blockTime) throws AccountIsLockedException {
        if (block) {
            long timePassed = System.currentTimeMillis() - blockTime.getTime();
            if (timePassed > 10000) {
                block = false;
            } else {
                throw new AccountIsLockedException(timePassed);
            }
        }
        return block;
    }
}
