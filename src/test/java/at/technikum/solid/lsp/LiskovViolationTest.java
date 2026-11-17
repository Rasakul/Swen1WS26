package at.technikum.solid.lsp;

import at.technikum.solid.lsp.accounts.BankAccount;
import at.technikum.solid.lsp.accounts.MinimumBalanceAccount;
import at.technikum.solid.lsp.shapes.refactored.Rectangle;
import at.technikum.solid.lsp.shapes.refactored.Square;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LiskovViolationTest {

    @Test
    void rectanglePreservesIndependentDimensions() {
        // GIVEN
        Rectangle rectangle = new Rectangle(2, 3);

        // WHEN
        rectangle.setWidth(5);
        rectangle.setHeight(4);

        // THEN
        assertEquals(5, rectangle.getWidth());
        assertEquals(4, rectangle.getHeight());
        assertEquals(20, rectangle.getArea());
    }

    @Test
    void squareBreaksTheRectangleContract() {
        // GIVEN
        Rectangle rectangle = new Square(2);

        // WHEN
        rectangle.setWidth(5);
        rectangle.setHeight(4);

        // THEN
        assertEquals(4, rectangle.getWidth());
        assertEquals(4, rectangle.getHeight());
        assertEquals(16, rectangle.getArea());
    }

    @Test
    void invalidResizeLeavesSquareUnchanged() {
        // GIVEN
        Rectangle square = new Square(2);

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> square.setWidth(0));
        assertThrows(IllegalArgumentException.class, () -> square.setHeight(-1));
        assertEquals(2, square.getWidth());
        assertEquals(2, square.getHeight());
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 3));
    }

    @Test
    void bankAccountAllowsWithdrawalWithinBalance() {
        // GIVEN
        BankAccount account = new BankAccount(new BigDecimal("100.00"));

        // WHEN
        account.withdraw(new BigDecimal("80.00"));

        // THEN
        assertEquals(new BigDecimal("20.00"), account.getBalance());
    }

    @Test
    void minimumBalanceAccountRejectsWithdrawalAllowedByBaseContract() {
        // GIVEN
        BankAccount account = new MinimumBalanceAccount(new BigDecimal("100.00"), new BigDecimal("50.00"));

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("80.00")));
        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }

    @Test
    void minimumBalanceAccountAllowsWithdrawalDownToMinimum() {
        // GIVEN
        BankAccount account = new MinimumBalanceAccount(new BigDecimal("100.00"), new BigDecimal("50.00"));

        // WHEN
        account.withdraw(new BigDecimal("50.00"));

        // THEN
        assertEquals(new BigDecimal("50.00"), account.getBalance());
    }

    @Test
    void accountsRejectInvalidWithdrawalsWithoutChangingBalance() {
        // GIVEN
        BankAccount[] accounts = {
            new BankAccount(new BigDecimal("100.00")),
            new MinimumBalanceAccount(new BigDecimal("100.00"), new BigDecimal("50.00"))
        };

        // WHEN / THEN
        for (BankAccount account : accounts) {
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(null));
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(BigDecimal.ZERO));
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("-1")));
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(new BigDecimal("101")));
            assertEquals(new BigDecimal("100.00"), account.getBalance());
        }
    }

    @Test
    void accountsRejectInvalidOpeningBalances() {
        // GIVEN
        BigDecimal openingBalance = new BigDecimal("100.00");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(null));
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(new BigDecimal("-1")));
        assertThrows(IllegalArgumentException.class, () -> new MinimumBalanceAccount(openingBalance, null));
        assertThrows(IllegalArgumentException.class,
                () -> new MinimumBalanceAccount(openingBalance, new BigDecimal("-1")));
        assertThrows(IllegalArgumentException.class,
                () -> new MinimumBalanceAccount(openingBalance, new BigDecimal("101")));
    }
}
