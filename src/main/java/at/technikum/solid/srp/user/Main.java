package at.technikum.solid.srp.user;

import at.technikum.solid.srp.order.OrderProcessor;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();
        UserService registration = new UserService(userRepository);

        registration.register("alex", "alex@technikum.at", "Secret123");
        registration.register("al", "not-an-email", "weak");
        registration.register("alex", "alex@technikum.at", "Secret123");

        OrderProcessor orders = new OrderProcessor();
        System.out.println(orders.process(
                "alex@technikum.at",
                List.of(new OrderProcessor.OrderItem("BOOK", 2, 19.90), new OrderProcessor.OrderItem("PEN", 10, 1.50)),
                "SAVE10"));
        try {
            orders.process("alex@technikum.at", List.of(new OrderProcessor.OrderItem("LAPTOP", 5, 999.0)), null);
        } catch (IllegalStateException e) {
            System.out.println("Order rejected: " + e.getMessage());
        }
    }
}
