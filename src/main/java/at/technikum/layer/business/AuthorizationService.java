package at.technikum.layer.business;

import at.technikum.layer.domain.User;

public interface AuthorizationService {

    String getToken(User user);

    boolean isTokenValid(String token);

    User getUserForToken(String token);

}
