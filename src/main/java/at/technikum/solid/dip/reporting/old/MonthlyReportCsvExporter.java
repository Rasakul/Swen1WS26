package at.technikum.solid.dip.reporting.old;

import java.util.List;

public class MonthlyReportCsvExporter {

    public String export(String month, List<String> reportLines) {
        if (month == null || month.isBlank() || reportLines == null || reportLines.isEmpty()) {
            throw new IllegalArgumentException("Month and report lines are required");
        }

        StringBuilder csv = new StringBuilder("month,report_line\n");
        for (String line : reportLines) {
            if (line == null || line.isBlank()) {
                throw new IllegalArgumentException("Report lines must not be blank");
            }
            csv.append(escape(month)).append(',').append(escape(line)).append('\n');
        }
        return csv.toString();
    }

    private static String escape(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
