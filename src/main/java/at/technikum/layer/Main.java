package at.technikum.layer;

import at.technikum.layer.business.UserService;
import at.technikum.layer.business.UserServiceImpl;
import at.technikum.layer.domain.User;
import at.technikum.layer.persistence.UserRepository;
import at.technikum.layer.persistence.UserRepositoryImpl;
import at.technikum.layer.presentation.UserController;
import at.technikum.layer.presentation.UserControllerImpl;

public class Main {


    public static void main(String[] args) {

        UserRepository userRepository = UserRepositoryImpl.getInstance();
        UserService userService =  UserServiceImpl.getInstance(userRepository);
        UserController userController = UserControllerImpl.getInstance(userService);

        User user = new User("user1", "email@technikum.at", "password");

        boolean loggedIn = userController.login(user);
        System.out.println("loggedIn: " + loggedIn);
    }
}
