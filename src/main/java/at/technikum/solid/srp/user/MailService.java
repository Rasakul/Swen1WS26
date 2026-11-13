package at.technikum.solid.srp.user;

public class MailService {

    public void sendMail(String username, String email) {
        String body = "Hello " + username + ",\n"
                + "welcome aboard! Your account for " + email + " is ready.\n"
                + "Regards, the Demo team";
        System.out.println("--- MAIL to " + email + " ---\n" + body + "\n--- END MAIL ---");
    }
}
