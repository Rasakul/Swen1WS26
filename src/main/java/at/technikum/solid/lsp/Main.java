package at.technikum.solid.lsp;

import at.technikum.solid.lsp.accounts.BankAccount;
import at.technikum.solid.lsp.accounts.MinimumBalanceAccount;
import at.technikum.solid.lsp.shapes.refactored.Rectangle;
import at.technikum.solid.lsp.shapes.refactored.Shape;
import at.technikum.solid.lsp.shapes.refactored.Square;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        resize(new Rectangle(2, 3));
        resize(new Square(2));

        withdrawFrom(new BankAccount(new BigDecimal("100.00")));
        withdrawFrom(new MinimumBalanceAccount(new BigDecimal("100.00"), new BigDecimal("50.00")));
    }

    private static void resize(Shape shape) {
        shape.setWidth(5);
        shape.setHeight(4);
        System.out.printf("Area %s",
                shape.getClass().getSimpleName(),
                shape.getWidth(), shape.getHeight(), shape.getArea());
    }

    private static void withdrawFrom(BankAccount account) {
        System.out.println(account.getClass().getSimpleName() + ": withdraw 80.00 from " + account.getBalance());
        try {
            account.withdraw(new BigDecimal("80.00"));
            System.out.println("Expected balance 20.00, actual balance " + account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Base-class contract broken: " + e.getMessage());
            System.out.println("Balance remains " + account.getBalance());
        }
    }
}
