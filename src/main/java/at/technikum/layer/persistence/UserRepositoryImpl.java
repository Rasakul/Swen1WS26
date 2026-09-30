package at.technikum.layer.persistence;

import at.technikum.layer.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl instance;

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    private final Map<String, User> users;

    private UserRepositoryImpl() {
        users = new HashMap<>();
        users.put("user1", new User("user1", "email@technikum.at", "password"));
    }

    @Override
    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    @Override
    public boolean validatePassword(User user) {
        if (userExists(user.getName())) {
            User retrieved = users.get(user.getName());
            return retrieved.getPassword().equals(user.getPassword());
        } else {
            return false;
        }
    }
}
