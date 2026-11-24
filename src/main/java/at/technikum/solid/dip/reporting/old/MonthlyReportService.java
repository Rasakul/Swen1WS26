package at.technikum.solid.dip.reporting.old;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MonthlyReportService {

    private static final BigDecimal VAT_RATE = new BigDecimal("0.20");

    public record Sale(String id, LocalDate date, String category, BigDecimal netAmount) {}

    private final SqlReportRepository repository = new SqlReportRepository();
    private final MonthlyReportCsvExporter csvExporter = new MonthlyReportCsvExporter();
    private final ReportAuditLogger auditLogger = new ReportAuditLogger();

    public List<String> publish(String month, List<Sale> sales) {
        YearMonth reportingMonth;
        try {
            reportingMonth = YearMonth.parse(month);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new IllegalArgumentException("Month must use YYYY-MM format", e);
        }
        if (sales == null || sales.isEmpty()) {
            throw new IllegalArgumentException("At least one sale is required");
        }

        Set<String> saleIds = new HashSet<>();
        Map<String, CategoryTotals> totalsByCategory = new TreeMap<>();
        for (Sale sale : sales) {
            validateSale(reportingMonth, sale, saleIds);
            totalsByCategory.computeIfAbsent(sale.category(), ignored -> new CategoryTotals()).add(sale.netAmount());
        }

        BigDecimal totalNet = BigDecimal.ZERO;
        BigDecimal totalVat = BigDecimal.ZERO;
        int totalSales = 0;
        List<String> reportLines = new java.util.ArrayList<>();
        reportLines.add("Monthly sales report: " + reportingMonth);
        reportLines.add("Category | Sales | Net | VAT (20%) | Gross");
        for (Map.Entry<String, CategoryTotals> entry : totalsByCategory.entrySet()) {
            CategoryTotals totals = entry.getValue();
            BigDecimal net = money(totals.net);
            BigDecimal vat = vatFor(net);
            BigDecimal gross = money(net.add(vat));

            reportLines.add(String.format(
                    "%s | %d | %s | %s | %s",
                    entry.getKey(), totals.count, net, vat, gross));
            totalSales += totals.count;
            totalNet = totalNet.add(net);
            totalVat = totalVat.add(vat);
        }
        reportLines.add(String.format(
                "TOTAL | %d | %s | %s | %s",
                totalSales, money(totalNet), money(totalVat), money(totalNet.add(totalVat))));

        repository.save(month, reportLines);
        String csv = csvExporter.export(month, reportLines);
        auditLogger.logPublished(month, totalSales, totalsByCategory.size());
        System.out.println("Published " + totalSales + " sales across " + totalsByCategory.size()
                + " categories for " + month);
        System.out.println("CSV export:\n" + csv);
        return List.copyOf(reportLines);
    }

    private static void validateSale(YearMonth reportingMonth, Sale sale, Set<String> saleIds) {
        if (sale == null) {
            throw new IllegalArgumentException("Sale must not be null");
        }
        if (sale.id() == null || sale.id().isBlank() || !saleIds.add(sale.id())) {
            throw new IllegalArgumentException("Sale IDs must be non-empty and unique");
        }
        if (sale.date() == null || !YearMonth.from(sale.date()).equals(reportingMonth)) {
            throw new IllegalArgumentException("Sale date must be within the reporting month");
        }
        if (sale.category() == null || sale.category().isBlank()) {
            throw new IllegalArgumentException("Sale category must not be blank");
        }
        if (sale.netAmount() == null
                || sale.netAmount().signum() <= 0
                || sale.netAmount().stripTrailingZeros().scale() > 2) {
            throw new IllegalArgumentException("Sale amount must be positive with at most two decimal places");
        }
    }

    private static BigDecimal vatFor(BigDecimal net) {
        return money(net.multiply(VAT_RATE));
    }

    private static BigDecimal money(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    private static final class CategoryTotals {
        private int count;
        private BigDecimal net = BigDecimal.ZERO;

        private void add(BigDecimal amount) {
            count++;
            net = net.add(amount);
        }
    }
}
