package at.technikum.solid.srp.user;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, String[]> userTable = new HashMap<>();

    public boolean exists(String username){
        if (userTable.containsKey(username)) {
            return false;
        }
        return true;
    }

    public void save(String username, String email, String passwordHash){
        userTable.put(username, new String[] {email, passwordHash});
    }

}
