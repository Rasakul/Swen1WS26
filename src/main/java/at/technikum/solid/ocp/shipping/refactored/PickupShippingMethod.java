package at.technikum.solid.ocp.shipping.refactored;

public class PickupShippingMethod implements ShippingMethod {

    @Override
    public double calculateCost(double weightKg, boolean international, boolean express) {
        double cost;

        if (international || express) {
            throw new IllegalArgumentException("PICKUP does not support international or express shipping");
        }
        cost = 0;

        return cost;
    }
}
