package org.example;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    MetricCalculator calculator = new MetricCalculator();
    @Test
    public void calculateResultTestTrue() {
        String input = "10 cm + 1 m - 10 mm";
        MetricCalculatorResult actual = calculator.calculate(input);
        MetricCalculatorResult expected = new MetricCalculatorResult(1090.0, "mm");

        Assert.assertEquals(expected,actual);
    }
    @Test
    public void calculateResultTestFalse() {
        String input = "10 cm + 1 m - 10 mm";
        MetricCalculatorResult actual = calculator.calculate(input);
        MetricCalculatorResult expected = new MetricCalculatorResult(1230.0, "cm");

        Assert.assertNotEquals(expected,actual);
    }
}
