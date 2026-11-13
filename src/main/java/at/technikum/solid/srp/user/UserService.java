package at.technikum.solid.srp.user;

public class UserService {

    private final UserValidator userValidator = new UserValidator();
    private final PasswordHasher passwordHasher = new PasswordHasher();
    private final MailService mailService = new MailService();

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(String username, String email, String password) {
        userValidator.validate(username, email, password);

        if(userRepository.exists(username)){
            Logger.log("REJECTED: user " + username + " already exists");
            return false;
        }

        String passwordHash = passwordHasher.hash(password);

        userRepository.save(username, email, passwordHash);

        mailService.sendMail(username, email);

        Logger.log("REGISTERED: " + username);
        return true;
    }

}
