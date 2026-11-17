package at.technikum.solid.ocp.shipping.refactored;

public class ShippingCostCalculator {

    public double calculate(String shippingMethod, double weightKg, boolean international, boolean express) {
        ShippingValidator.validate(shippingMethod, weightKg);

        double cost = ShippingMethodFactory.get(shippingMethod).calculateCost(weightKg, international, express);

        return Math.round(cost * 100) / 100.0;
    }

}
