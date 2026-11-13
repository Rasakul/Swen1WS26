package at.technikum.solid.srp.user;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    public boolean validate(String username, String email, String password){
        if (username == null || username.isBlank() || username.length() < 3) {
            Logger.log("REJECTED: invalid username");
            return false;
        }
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            Logger.log("REJECTED: invalid email '" + email + "'");
            return false;
        }
        if (password == null
                || password.length() < 8
                || password.chars().noneMatch(Character::isDigit)
                || password.chars().noneMatch(Character::isUpperCase)) {
            Logger.log("REJECTED: weak password for " + username);
            return false;
        }
        return true;
    }
}
