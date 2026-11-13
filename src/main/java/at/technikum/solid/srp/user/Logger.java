package at.technikum.solid.srp.user;

import java.time.LocalDateTime;

public class Logger {

    public static void log(String message) {
        System.out.println("[AUDIT " + LocalDateTime.now().withNano(0) + "] " + message);
    }

}
