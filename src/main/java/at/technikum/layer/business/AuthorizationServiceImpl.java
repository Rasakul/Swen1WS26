package at.technikum.layer.business;

import at.technikum.layer.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthorizationServiceImpl implements AuthorizationService{

    private final Map<User, String> tokenMap = new HashMap<>();

    @Override
    public String getToken(User user) {
        String token = UUID.randomUUID().toString();
        tokenMap.put(user, token);
        return token;
    }

    @Override
    public boolean isTokenValid(String token) {
        return tokenMap.containsValue(token);
    }

    @Override
    public User getUserForToken(String token) {
        return tokenMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(token))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
