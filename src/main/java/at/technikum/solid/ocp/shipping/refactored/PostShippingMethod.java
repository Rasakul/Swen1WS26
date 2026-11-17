package at.technikum.solid.ocp.shipping.refactored;

public class PostShippingMethod implements ShippingMethod {
    @Override
    public double calculateCost(double weightKg, boolean international, boolean express) {
        if (weightKg > 30) {
            throw new IllegalArgumentException("POST only accepts parcels up to 30 kg");
        }
        double cost = 4.90 + Math.ceil(weightKg) * 0.80;
        if (international) {
            cost += 8;
        }
        if (express) {
            cost += 5;
        }

        return cost;
    }
}
