package at.technikum.solid.ocp.shipping.refactored;

public interface ShippingMethod {

    double calculateCost(double weightKg, boolean international, boolean express);

}
