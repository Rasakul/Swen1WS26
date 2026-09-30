package at.technikum.layer.persistence;

import at.technikum.layer.domain.User;

public interface UserRepository {

    boolean userExists(String username);

    boolean validatePassword(User user);

}
