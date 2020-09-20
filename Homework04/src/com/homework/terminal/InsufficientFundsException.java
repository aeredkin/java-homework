package com.homework.terminal;

// Чтобы избежать это исключение нужно ввести сумму не больше той, что есть на счёте.
public class InsufficientFundsException extends Exception {
    @Override
    public String getMessage() {
        return "Недостаточно средств на счёте.";
    }
}
