package at.technikum.solid.dip.notifications.refactored;

public class SmtpEmailSender implements EmailSender{

    public void send(String recipient, String message) {
        if (recipient == null || recipient.isBlank() || message == null || message.isBlank()) {
            throw new IllegalArgumentException("Recipient and message are required");
        }
        System.out.println("SMTP sent email to " + recipient + ": " + message);
    }
}
