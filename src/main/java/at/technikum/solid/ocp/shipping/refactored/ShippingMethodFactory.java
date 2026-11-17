package at.technikum.solid.ocp.shipping.refactored;

import java.util.HashMap;
import java.util.Map;

public class ShippingMethodFactory {

    private static final Map<String, ShippingMethod> shippingMethods = new HashMap<>();

    static {
        shippingMethods.put("POST", new PostShippingMethod());
        shippingMethods.put("COURIER", new CourierShippingMethod());
        shippingMethods.put("PICKUP", new PickupShippingMethod());
    }

    public static ShippingMethod get(String shippingMethod) {
        return shippingMethods.getOrDefault(shippingMethod, null);
    }

}
