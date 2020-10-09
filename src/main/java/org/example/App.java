package org.example;


public class App 
{
    public static void main( String[] args ) {
        MetricCalculator metricCalculator = new MetricCalculator();
        String input = "10 cm + 1 m - 10 mm";
        System.out.println(metricCalculator.calculate(input).toString());
    }
}
