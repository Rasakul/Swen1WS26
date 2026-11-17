package at.technikum.solid.ocp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import at.technikum.solid.ocp.shipping.refactored.ShippingCostCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ShippingCostCalculatorTest {

    @ParameterizedTest
    @CsvSource({
        "POST, 2.5, false, false, 7.30",
        "POST, 2.5, true, true, 20.30",
        "POST, 30, false, false, 28.90",
        "COURIER, 2.5, false, false, 15.75",
        "COURIER, 12, true, true, 52.00",
        "COURIER, 70, false, false, 117.00",
        "PICKUP, 2.5, false, false, 0.00"
    })
    void calculatesShippingCost(String method, double weight, boolean international, boolean express, double expected) {
        // GIVEN
        ShippingCostCalculator calculator = new ShippingCostCalculator();

        // WHEN
        double cost = calculator.calculate(method, weight, international, express);

        // THEN
        assertEquals(expected, cost, 0.001);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "DRONE"})
    void rejectsMissingOrUnsupportedMethods(String method) {
        // GIVEN
        ShippingCostCalculator calculator = new ShippingCostCalculator();

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(method, 2, false, false));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rejectsInvalidWeight(double weight) {
        // GIVEN
        ShippingCostCalculator calculator = new ShippingCostCalculator();

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("POST", weight, false, false));
    }

    @ParameterizedTest
    @CsvSource({
        "POST, 30.1, false, false",
        "COURIER, 70.1, false, false",
        "PICKUP, 2, true, false",
        "PICKUP, 2, false, true"
    })
    void rejectsUnsupportedShipmentOptions(String method, double weight, boolean international, boolean express) {
        // GIVEN
        ShippingCostCalculator calculator = new ShippingCostCalculator();

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(method, weight, international, express));
    }
}
