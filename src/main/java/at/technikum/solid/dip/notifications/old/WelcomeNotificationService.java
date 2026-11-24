package at.technikum.solid.dip.notifications.old;

// DIP violation: high-level notification policy constructs a concrete SMTP sender.
public class WelcomeNotificationService {

    private final SmtpEmailSender sender = new SmtpEmailSender();

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
