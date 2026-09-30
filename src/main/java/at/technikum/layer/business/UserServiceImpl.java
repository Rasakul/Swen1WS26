package at.technikum.layer.business;

import at.technikum.layer.domain.User;
import at.technikum.layer.persistence.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserService instance;

    private final UserRepository userRepository;

    public static UserService getInstance(UserRepository userRepository) {
        if (instance == null) {
            instance = new UserServiceImpl(userRepository);
        }
        return instance;
    }

    private final List<User> loggedInUsers = new ArrayList<>();

    private UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(User user) {

        boolean validated = userRepository.validatePassword(user);

        if (validated) {
            loggedInUsers.add(user);
            return true;
        } else {
            return false;
        }
    }

}
