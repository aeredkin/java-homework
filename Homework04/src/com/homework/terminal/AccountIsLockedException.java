package com.homework.terminal;

// Чтобы избежать это исключение нужно вводить ПИН-код больше чем через 10 секунд после блокировки.
public class AccountIsLockedException extends Exception {
    private final long timePassed;

    public AccountIsLockedException(long timePassed) {
        this.timePassed = timePassed;
    }

    @Override
    public String getMessage() {
        return "До снятия блокировки осталось " + (10 - timePassed / 1000) + " секунд.";
    }
}
