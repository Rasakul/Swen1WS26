package at.technikum.solid.lsp.accounts;

import java.math.BigDecimal;

public class BankAccount {

    private BigDecimal balance;

    public BankAccount(BigDecimal openingBalance) {
        if (openingBalance == null || openingBalance.signum() < 0) {
            throw new IllegalArgumentException("Opening balance must not be null or negative");
        }
        balance = openingBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    // Contract: any positive amount up to the current balance can be withdrawn.
    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Withdrawal must be a positive amount");
        }
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance = balance.subtract(amount);
    }
}
