package at.technikum.solid.ocp.inventory;

import java.util.List;

// OCP violation: adding a report format requires modifying this exporter.
public class InventoryReportExporter {

    public record StockItem(String product, int quantity) {}

    public String export(String format, List<StockItem> items) {
        if (format == null || format.isBlank()) {
            throw new IllegalArgumentException("Report format is required");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Report must contain at least one stock item");
        }
        for (StockItem item : items) {
            if (item == null || item.product() == null || item.product().isBlank()) {
                throw new IllegalArgumentException("Each stock item needs a product name");
            }
            if (item.quantity() < 0) {
                throw new IllegalArgumentException("Stock quantity cannot be negative");
            }
        }

        StringBuilder report = new StringBuilder();
        switch (format) {
            case "CSV" -> {
                report.append("Product,Quantity\r\n");
                for (StockItem item : items) {
                    String escapedProduct = item.product().replace("\"", "\"\"");
                    report.append('"').append(escapedProduct).append("\",")
                            .append(item.quantity()).append("\r\n");
                }
            }
            case "HTML" -> {
                report.append("<table><thead><tr><th>Product</th><th>Quantity</th></tr></thead><tbody>");
                for (StockItem item : items) {
                    String escapedProduct = item.product()
                            .replace("&", "&amp;")
                            .replace("<", "&lt;")
                            .replace(">", "&gt;")
                            .replace("\"", "&quot;")
                            .replace("'", "&#39;");
                    report.append("<tr><td>").append(escapedProduct).append("</td><td>")
                            .append(item.quantity()).append("</td></tr>");
                }
                report.append("</tbody></table>");
            }
            case "TEXT" -> {
                report.append("Inventory report\n");
                for (StockItem item : items) {
                    report.append(item.product()).append(": ").append(item.quantity())
                            .append(item.quantity() == 1 ? " unit\n" : " units\n");
                }
            }
            default -> throw new IllegalArgumentException("Unsupported report format: " + format);
        }
        return report.toString();
    }
}
