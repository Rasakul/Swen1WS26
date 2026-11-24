package at.technikum.solid.dip.reporting.old;

public class ReportAuditLogger {

    public void logPublished(String month, int saleCount, int categoryCount) {
        if (month == null || month.isBlank() || saleCount <= 0 || categoryCount <= 0) {
            throw new IllegalArgumentException("Month and positive report counts are required");
        }
        System.out.printf(
                "[AUDIT] Monthly report %s published: %d sales across %d categories%n",
                month, saleCount, categoryCount);
    }
}
