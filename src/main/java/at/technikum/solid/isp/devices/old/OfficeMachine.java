package at.technikum.solid.isp.devices.old;

public interface OfficeMachine {
    void print(String document);

    void scan(String document);

    void fax(String document, String phoneNumber);
}
