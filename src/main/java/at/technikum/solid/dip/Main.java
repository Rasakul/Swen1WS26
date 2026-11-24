package at.technikum.solid.dip;

import at.technikum.solid.dip.notifications.refactored.DependencyManager;
import at.technikum.solid.dip.reporting.old.MonthlyReportService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== OLD Example 1: Report service tied to SQL, CSV, and audit implementations ===");
        MonthlyReportService reports = new MonthlyReportService();
        List<String> report = reports.publish("2026-11", List.of(
                new MonthlyReportService.Sale("S-101", LocalDate.of(2026, 11, 3), "Books", new BigDecimal("45.00")),
                new MonthlyReportService.Sale("S-102", LocalDate.of(2026, 11, 4), "Books", new BigDecimal("30.00")),
                new MonthlyReportService.Sale("S-103", LocalDate.of(2026, 11, 4), "Stationery", new BigDecimal("12.50")),
                new MonthlyReportService.Sale("S-104", LocalDate.of(2026, 11, 9), "Stationery", new BigDecimal("8.25"))));
        report.forEach(System.out::println);
        try {
            reports.publish("2026-11", List.of(
                    new MonthlyReportService.Sale(
                            "S-105", LocalDate.of(2026, 10, 31), "Books", new BigDecimal("15.00"))));
        } catch (IllegalArgumentException e) {
            System.out.println("Sale rejected: " + e.getMessage());
        }

        System.out.println("\n=== OLD Example 2: Notification service tied to SMTP ===");
        at.technikum.solid.dip.notifications.old.WelcomeNotificationService oldNotifications =
                new at.technikum.solid.dip.notifications.old.WelcomeNotificationService();
        oldNotifications.sendWelcome("alex@example.com", "Alex");

        System.out.println("\n=== REFACTORED notification service using an abstraction ===");
        DependencyManager dependencyManager = new DependencyManager();
        dependencyManager.inject();

        dependencyManager.welcomeNotificationService.sendWelcome("alex@example.com", "Alex");
        try {
            dependencyManager.welcomeNotificationService.sendWelcome("not-an-email", "Alex");
        } catch (IllegalArgumentException e) {
            System.out.println("Notification rejected: " + e.getMessage());
        }
    }
}
