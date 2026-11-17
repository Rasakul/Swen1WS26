package at.technikum.solid.ocp.shipping.refactored;

public class ShippingValidator {
    public static void validate(String shippingMethod, double weightKg) {
        if (shippingMethod == null || shippingMethod.isBlank()) {
            throw new IllegalArgumentException("Shipping method is required");
        }
        if (!Double.isFinite(weightKg) || weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be a positive, finite number");
        }
    }
}
