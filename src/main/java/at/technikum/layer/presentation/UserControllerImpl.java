package at.technikum.layer.presentation;

import at.technikum.layer.business.UserService;
import at.technikum.layer.business.UserServiceImpl;
import at.technikum.layer.domain.User;

import java.util.List;

public class UserControllerImpl implements UserController {

    private static UserController instance;

    private final UserService userService;

    public static UserController getInstance(UserService userService) {
        if(instance == null) {
            instance = new UserControllerImpl(userService);
        }
        return instance;
    }

    private UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean login(User user) {
        return userService.login(user);
    }

    @Override
    public User create(User user) {
        return userService.create(user);
    }

    @Override
    public User get(String id) {
        return userService.get(id);
    }

    @Override
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    public User update(User user) {
        return userService.update(user);
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

}
