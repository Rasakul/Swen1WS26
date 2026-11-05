package at.technikum.layer.persistence;

import at.technikum.layer.domain.User;

import java.util.List;

public interface UserRepository {

    boolean userExists(String username);

    boolean validatePassword(User user);

    User create(User user);

    User get(String id);

    List<User> getAll();

    User update(User user);

    boolean delete(User user);
}
