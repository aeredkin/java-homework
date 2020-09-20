package com.homework.terminal;

public class TerminalServer {
    private int fund = 1000;

    public int getFund() {
        return fund;
    }

    public void changeAmount(int change) throws InsufficientFundsException {
        if (fund + change < 0) {
            throw new InsufficientFundsException();
        } else if (change % 100 != 0) {
            throw new IllegalArgumentException("Сумма должна быть кратна 100.");
        }
        fund += change;
    }
}
