package at.technikum.solid.lsp.accounts;

import java.math.BigDecimal;

public class MinimumBalanceAccount extends BankAccount {

    private final BigDecimal minimumBalance;

    public MinimumBalanceAccount(BigDecimal openingBalance, BigDecimal minimumBalance) {
        super(openingBalance);
        if (minimumBalance == null || minimumBalance.signum() < 0
                || minimumBalance.compareTo(openingBalance) > 0) {
            throw new IllegalArgumentException("Minimum balance must be between zero and the opening balance");
        }
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Withdrawal must be a positive amount");
        }
        if (getBalance().subtract(amount).compareTo(minimumBalance) < 0) {
            throw new IllegalArgumentException("Withdrawal would violate the minimum balance of " + minimumBalance);
        }
        super.withdraw(amount);
    }
}
