package at.technikum.solid.ocp;

import at.technikum.solid.ocp.inventory.InventoryReportExporter;
import at.technikum.solid.ocp.shipping.refactored.ShippingCostCalculator;

import java.util.Locale;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Example 1: Shipping costs ===");
        demonstrateShippingCosts();

        System.out.println("\n=== Example 2: Inventory report export ===");
        demonstrateReportExport();
    }

    private static void demonstrateShippingCosts() {
        ShippingCostCalculator calculator = new ShippingCostCalculator();

        for (String method : new String[] {"POST", "COURIER", "PICKUP"}) {
            double cost = calculator.calculate(method, 2.5, false, false);
            System.out.printf(Locale.ROOT, "%s, domestic, 2.5 kg: EUR %.2f%n", method, cost);
        }

        double expressCost = calculator.calculate("COURIER", 12, true, true);
        System.out.printf(Locale.ROOT, "COURIER, international express, 12 kg: EUR %.2f%n", expressCost);

        try {
            calculator.calculate("POST", 35, false, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Shipment rejected: " + e.getMessage());
        }

        // Supporting DRONE requires modifying the calculator instead of adding an implementation.
        try {
            calculator.calculate("DRONE", 2, false, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot extend shipping methods: " + e.getMessage());
        }
    }

    private static void demonstrateReportExport() {
        InventoryReportExporter exporter = new InventoryReportExporter();
        List<InventoryReportExporter.StockItem> items = List.of(
                new InventoryReportExporter.StockItem("Notebook \"A5\"", 12),
                new InventoryReportExporter.StockItem("Pen & pencil set", 1),
                new InventoryReportExporter.StockItem("Folder", 0));

        for (String format : new String[] {"CSV", "HTML", "TEXT"}) {
            System.out.println("--- " + format + " ---");
            System.out.println(exporter.export(format, items));
        }

        try {
            exporter.export("CSV", List.of(new InventoryReportExporter.StockItem("Notebook", -1)));
        } catch (IllegalArgumentException e) {
            System.out.println("Report rejected: " + e.getMessage());
        }

        // Supporting XML requires changing the exporter rather than adding a new formatter.
        try {
            exporter.export("XML", items);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot extend report formats: " + e.getMessage());
        }
    }
}
