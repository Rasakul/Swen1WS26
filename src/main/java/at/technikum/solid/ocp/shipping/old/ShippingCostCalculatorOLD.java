package at.technikum.solid.ocp.shipping.old;

public class ShippingCostCalculatorOLD {

    public double calculate(String shippingMethod, double weightKg, boolean international, boolean express) {
        if (shippingMethod == null || shippingMethod.isBlank()) {
            throw new IllegalArgumentException("Shipping method is required");
        }
        if (!Double.isFinite(weightKg) || weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be a positive, finite number");
        }

        double cost;
        switch (shippingMethod) {
            case "POST" -> {
                if (weightKg > 30) {
                    throw new IllegalArgumentException("POST only accepts parcels up to 30 kg");
                }
                cost = 4.90 + Math.ceil(weightKg) * 0.80;
                if (international) {
                    cost += 8;
                }
                if (express) {
                    cost += 5;
                }
            }
            case "COURIER" -> {
                if (weightKg > 70) {
                    throw new IllegalArgumentException("COURIER only accepts parcels up to 70 kg");
                }
                cost = 12 + weightKg * 1.50;
                if (international) {
                    cost *= 1.40;
                }
                if (express) {
                    cost += 10;
                }
            }
            case "PICKUP" -> {
                if (international || express) {
                    throw new IllegalArgumentException("PICKUP does not support international or express shipping");
                }
                cost = 0;
            }
            default -> throw new IllegalArgumentException("Unsupported shipping method: " + shippingMethod);
        }
        return Math.round(cost * 100) / 100.0;
    }
}
