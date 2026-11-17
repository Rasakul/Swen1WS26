package at.technikum.solid;

import at.technikum.solid.lsp.shapes.old.RectangleOLD;
import at.technikum.solid.lsp.shapes.old.SquareOLD;
import at.technikum.solid.ocp.shipping.old.ShippingCostCalculatorOLD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OriginalExamplesTest {

    @Test
    void preservesOriginalShippingPrices() {
        // GIVEN
        ShippingCostCalculatorOLD calculator = new ShippingCostCalculatorOLD();

        // WHEN / THEN
        assertEquals(7.30, calculator.calculate("POST", 2.5, false, false), 0.001);
        assertEquals(20.30, calculator.calculate("POST", 2.5, true, true), 0.001);
        assertEquals(52.00, calculator.calculate("COURIER", 12, true, true), 0.001);
        assertEquals(0.00, calculator.calculate("PICKUP", 2.5, false, false), 0.001);
    }

    @Test
    void preservesOriginalShippingValidation() {
        // GIVEN
        ShippingCostCalculatorOLD calculator = new ShippingCostCalculatorOLD();

        // WHEN / THEN
        for (String method : new String[] {null, "", " ", "DRONE"}) {
            assertThrows(IllegalArgumentException.class, () -> calculator.calculate(method, 2, false, false));
        }
        for (double weight : new double[] {0, -1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> calculator.calculate("POST", weight, false, false));
        }
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("POST", 30.1, false, false));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("COURIER", 70.1, false, false));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("PICKUP", 2, true, false));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("PICKUP", 2, false, true));
    }

    @Test
    void preservesOriginalShapeBehaviorAndViolation() {
        // GIVEN
        RectangleOLD rectangle = new RectangleOLD(2, 3);
        RectangleOLD square = new SquareOLD(2);

        // WHEN
        rectangle.setWidth(5);
        rectangle.setHeight(4);
        square.setWidth(5);
        square.setHeight(4);

        // THEN
        assertEquals(5, rectangle.getWidth());
        assertEquals(4, rectangle.getHeight());
        assertEquals(20, rectangle.getArea());
        assertEquals(4, square.getWidth());
        assertEquals(4, square.getHeight());
        assertEquals(16, square.getArea());
    }

    @Test
    void preservesOriginalShapeValidation() {
        // GIVEN
        RectangleOLD[] shapes = {new RectangleOLD(2, 3), new SquareOLD(2)};

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> new RectangleOLD(0, 3));
        assertThrows(IllegalArgumentException.class, () -> new RectangleOLD(2, -1));
        assertThrows(IllegalArgumentException.class, () -> new SquareOLD(0));
        for (RectangleOLD shape : shapes) {
            int width = shape.getWidth();
            int height = shape.getHeight();
            assertThrows(IllegalArgumentException.class, () -> shape.setWidth(0));
            assertThrows(IllegalArgumentException.class, () -> shape.setHeight(-1));
            assertEquals(width, shape.getWidth());
            assertEquals(height, shape.getHeight());
        }
    }
}
