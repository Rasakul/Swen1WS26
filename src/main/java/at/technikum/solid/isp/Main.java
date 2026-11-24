package at.technikum.solid.isp;

import at.technikum.solid.isp.devices.old.BasicPrinter;
import at.technikum.solid.isp.devices.old.OfficeMachine;
import at.technikum.solid.isp.devices.refactored.AdvancedPrinter;
import at.technikum.solid.isp.devices.refactored.FhMachine;
import at.technikum.solid.isp.devices.refactored.Printable;
import at.technikum.solid.isp.team.old.FinanceManager;
import at.technikum.solid.isp.team.old.JuniorDeveloper;
import at.technikum.solid.isp.team.old.ReleaseLead;
import at.technikum.solid.isp.team.old.TeamMember;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== OLD Example 1: Print-only device forced into a broad interface ===");
        OfficeMachine printer = new BasicPrinter();
        printer.print("Quarterly report");
        try {
            printer.scan("Quarterly report");
        } catch (UnsupportedOperationException e) {
            System.out.println("Unsupported operation: " + e.getMessage());
        }
        try {
            printer.fax("Quarterly report", "+1-555-0100");
        } catch (UnsupportedOperationException e) {
            System.out.println("Unsupported operation: " + e.getMessage());
        }

        System.out.println("\n=== REFACTORED device interfaces ===");
        Printable simplePrinter = new at.technikum.solid.isp.devices.refactored.BasicPrinter();
        simplePrinter.print("Quarterly report");
        FhMachine advancedPrinter = new AdvancedPrinter();
        System.out.println("Advanced printer supports only the print and scan capabilities it implements");
        advancedPrinter.print("Quarterly report");
        advancedPrinter.scan("Quarterly report");

        System.out.println("\n=== OLD Example 2: Role forced into management operations ===");
        TeamMember developer = new JuniorDeveloper("Sam");
        developer.workOn("profile page");
        try {
            developer.approveExpense("Conference ticket", 250);
        } catch (UnsupportedOperationException e) {
            System.out.println("Unsupported operation: " + e.getMessage());
        }
        try {
            developer.deployRelease("v1.2.0");
        } catch (UnsupportedOperationException e) {
            System.out.println("Unsupported operation: " + e.getMessage());
        }

        System.out.println("\nRelease lead supports task work and deployments, but not expense approval:");
        TeamMember releaseLead = new ReleaseLead("Taylor");
        releaseLead.workOn("release checklist");
        releaseLead.deployRelease("v1.2.0");
        runUnsupported("expense approval", () -> releaseLead.approveExpense("Conference ticket", 250));

        System.out.println("\nFinance manager supports expense approval, but not development or deployment:");
        TeamMember financeManager = new FinanceManager("Morgan");
        financeManager.approveExpense("Conference ticket", 250);
        runUnsupported("task work", () -> financeManager.workOn("profile page"));
        runUnsupported("release deployment", () -> financeManager.deployRelease("v1.2.0"));
    }

    private static void runUnsupported(String operation, Runnable action) {
        try {
            action.run();
        } catch (UnsupportedOperationException e) {
            System.out.println("Unsupported " + operation + ": " + e.getMessage());
        }
    }
}
