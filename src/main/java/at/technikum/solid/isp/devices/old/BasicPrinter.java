package at.technikum.solid.isp.devices.old;

// ISP violation: print-only devices must implement unrelated scanner and fax operations.
public class BasicPrinter implements OfficeMachine {

    @Override
    public void print(String document) {
        if (document == null || document.isBlank()) {
            throw new IllegalArgumentException("Document must not be blank");
        }
        System.out.println("Printed document: " + document);
    }

    @Override
    public void scan(String document) {
        throw new UnsupportedOperationException("This printer cannot scan");
    }

    @Override
    public void fax(String document, String phoneNumber) {
        throw new UnsupportedOperationException("This printer cannot send faxes");
    }
}
