package at.technikum.solid.ocp.shipping.refactored;

public class CourierShippingMethod implements ShippingMethod {
    @Override
    public double calculateCost(double weightKg, boolean international, boolean express) {
        double cost;

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

        return cost;
    }
}
