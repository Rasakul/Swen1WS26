package at.technikum.solid.isp.devices.refactored;

public class BasicPrinter implements Printable {

    @Override
    public void print(String document) {
        if (document == null || document.isBlank()) {
            throw new IllegalArgumentException("Document must not be blank");
        }
        System.out.println("Printed document: " + document);
    }
}
