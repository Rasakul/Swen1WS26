package at.technikum.solid.dip.notifications.refactored;

public interface EmailSender {
    void send(String recipient, String message);
}
