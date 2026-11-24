package at.technikum.solid.dip.notifications.refactored;

public class WelcomeNotificationService {

    private final EmailSender sender;

    public WelcomeNotificationService(EmailSender sender) {
        this.sender = sender;
    }

    public void sendWelcome(String email, String name) {
        if (email == null || !email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            throw new IllegalArgumentException("A valid recipient email is required");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Recipient name is required");
        }

        sender.send(email, "Welcome, " + name + "!");
    }
}
