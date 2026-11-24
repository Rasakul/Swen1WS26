package at.technikum.solid.dip.notifications.refactored;

public class DependencyManager {

    public EmailSender emailSender;
    public WelcomeNotificationService welcomeNotificationService;

    public void inject(){
        this.emailSender = new SmtpEmailSender();
        this.welcomeNotificationService = new WelcomeNotificationService(this.emailSender);
    }
}
