package at.technikum.solid.ocp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import at.technikum.solid.ocp.inventory.InventoryReportExporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InventoryReportExporterTest {

    @Test
    void exportsCsvWithEscapedQuotesCommasAndNewlines() {
        // GIVEN
        InventoryReportExporter exporter = new InventoryReportExporter();
        List<InventoryReportExporter.StockItem> items = List.of(
                new InventoryReportExporter.StockItem("Notebook, \"A5\"\nBlue", 12));

        // WHEN
        String report = exporter.export("CSV", items);

        // THEN
        assertEquals("Product,Quantity\r\n\"Notebook, \"\"A5\"\"\nBlue\",12\r\n", report);
    }

    @Test
    void exportsHtmlWithEscapedProductNames() {
        // GIVEN
        InventoryReportExporter exporter = new InventoryReportExporter();
        List<InventoryReportExporter.StockItem> items = List.of(
                new InventoryReportExporter.StockItem("<Pen> & \"pencil's\"", 1));

        // WHEN
        String report = exporter.export("HTML", items);

        // THEN
        assertEquals("<table><thead><tr><th>Product</th><th>Quantity</th></tr></thead><tbody>"
                + "<tr><td>&lt;Pen&gt; &amp; &quot;pencil&#39;s&quot;</td><td>1</td></tr>"
                + "</tbody></table>", report);
    }

    @Test
    void exportsTextWithZeroSingularAndPluralQuantities() {
        // GIVEN
        InventoryReportExporter exporter = new InventoryReportExporter();
        List<InventoryReportExporter.StockItem> items = List.of(
                new InventoryReportExporter.StockItem("Folder", 0),
                new InventoryReportExporter.StockItem("Pen", 1),
                new InventoryReportExporter.StockItem("Notebook", 12));

        // WHEN
        String report = exporter.export("TEXT", items);

        // THEN
        assertEquals("Inventory report\nFolder: 0 units\nPen: 1 unit\nNotebook: 12 units\n", report);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "XML"})
    void rejectsMissingOrUnsupportedFormat(String format) {
        // GIVEN
        InventoryReportExporter exporter = new InventoryReportExporter();
        List<InventoryReportExporter.StockItem> items = List.of(
                new InventoryReportExporter.StockItem("Pen", 1));

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> exporter.export(format, items));
    }

    @Test
    void rejectsMissingOrInvalidStockItems() {
        // GIVEN
        InventoryReportExporter exporter = new InventoryReportExporter();
        List<List<InventoryReportExporter.StockItem>> invalidReports = Arrays.asList(
                null,
                List.of(),
                Arrays.asList((InventoryReportExporter.StockItem) null),
                List.of(new InventoryReportExporter.StockItem(null, 1)),
                List.of(new InventoryReportExporter.StockItem(" ", 1)),
                List.of(new InventoryReportExporter.StockItem("Pen", -1)));

        // WHEN / THEN
        for (List<InventoryReportExporter.StockItem> items : invalidReports) {
            assertThrows(IllegalArgumentException.class, () -> exporter.export("CSV", items));
        }
    }
}
