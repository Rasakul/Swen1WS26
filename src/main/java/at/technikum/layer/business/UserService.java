package at.technikum.layer.business;

import at.technikum.layer.domain.User;

import java.util.List;

public interface UserService {

    boolean login(User user);

    User create(User user);

    User get(String id);

    List<User> getAll();

    User update(User user);

    boolean delete(User user);
}
