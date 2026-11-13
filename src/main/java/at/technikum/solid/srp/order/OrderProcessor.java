package at.technikum.solid.srp.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OrderProcessor {

    public record OrderItem(String sku, int quantity, double unitPrice) {}

    private static final double VAT_RATE = 0.20;

    private final Map<String, Integer> stock = new HashMap<>(Map.of("BOOK", 10, "PEN", 100, "LAPTOP", 2));
    private final List<String> orderStorage = new ArrayList<>();

    public String process(String customerEmail, List<OrderItem> items, String couponCode) {

        if (customerEmail == null || !customerEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid customer email");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        for (OrderItem item : items) {
            if (item.quantity() <= 0 || item.unitPrice() < 0) {
                throw new IllegalArgumentException("Invalid quantity or price for " + item.sku());
            }
            if (stock.getOrDefault(item.sku(), 0) < item.quantity()) {
                throw new IllegalStateException("Not enough stock for " + item.sku());
            }
        }

        double net = 0;
        int totalQuantity = 0;
        for (OrderItem item : items) {
            net += item.unitPrice() * item.quantity();
            totalQuantity += item.quantity();
        }
        if (totalQuantity >= 10) {
            net *= 0.95;
        }
        if ("SAVE10".equals(couponCode)) {
            net *= 0.90;
        }
        double vat = net * VAT_RATE;
        double gross = net + vat;

        for (OrderItem item : items) {
            stock.merge(item.sku(), -item.quantity(), Integer::sum);
        }

        StringBuilder receipt = new StringBuilder("Receipt for " + customerEmail + "\n");
        for (OrderItem item : items) {
            receipt.append(String.format(
                    Locale.ROOT, "  %dx %s @ %.2f%n", item.quantity(), item.sku(), item.unitPrice()));
        }
        receipt.append(String.format(Locale.ROOT, "  Net: %.2f | VAT: %.2f | Total: %.2f", net, vat, gross));

        orderStorage.add(String.format(Locale.ROOT, "%s;%d;%.2f", customerEmail, totalQuantity, gross));

        return receipt.toString();
    }

    public List<String> getStoredOrders() {
        return orderStorage;
    }
}
