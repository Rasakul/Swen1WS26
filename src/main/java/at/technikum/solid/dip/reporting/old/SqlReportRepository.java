package at.technikum.solid.dip.reporting.old;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlReportRepository {

    private final Map<String, List<String>> rowsByMonth = new HashMap<>();

    public void save(String month, List<String> reportLines) {
        rowsByMonth.put(month, List.copyOf(reportLines));
        System.out.println("SQL repository stored " + reportLines.size() + " lines for " + month);
    }
}
