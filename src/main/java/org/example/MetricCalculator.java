package org.example;

import java.util.ArrayList;
import java.util.List;

public class MetricCalculator {
    public static final String[] METRIC_ORDER = new String[]{"mm", "cm", "dm", "m", "km"};
    public static final double[] MM_SCALING = new double[]{1.0, 10, 100, 1000, 10000};
    public static final double[] CM_SCALING = new double[]{0.1, 1, 10, 100, 1000};
    public static final double[] DM_SCALING = new double[]{0.01, 0.1, 1, 10, 100};
    public static final double[] M_SCALING = new double[]{0.001, 0.01, 0.1, 1, 10};
    public static final double[] KM_SCALING = new double[]{0.0001, 0.001, 0.01, 0.1, 1};
    public static final int MM_INDEX = 0, CM_INDEX = 1, DM_INDEX = 2, M_INDEX = 3, KM_INDEX = 4;

    public MetricCalculatorResult calculate(String input) {
        List<Character> inputOperators = getInputOperators(input);

        List<String> valuesWithMetrics = getValuesWithMetrics(input);

        List<Double> inputNumbers = getInputNumbers(valuesWithMetrics);
        List<String> inputMetrics = getInputMetrics(valuesWithMetrics);

        int minMetricIndex = getMinimumMetricIndex(inputMetrics);
        String minMetric = METRIC_ORDER[minMetricIndex];

        double[] metricScale = getMetricScale(minMetricIndex);

        List<Double> inputNumbersConverted = getNumbersConvertedToMetricScale(inputMetrics, inputNumbers, metricScale);

        double result = calculateResult(inputOperators, inputNumbersConverted);

        return new MetricCalculatorResult(result, minMetric);
    }

    private static List<Character> getInputOperators(String input) {
        List<Character> inputOperators = new ArrayList<>();
        for (Character c : input.toCharArray()) {
            if (c == '+' || c == '-') {
                inputOperators.add(c);
            }
        }
        return inputOperators;
    }

    private static List<String> getValuesWithMetrics(String input) {
        String[] inputValues = input.split("\\+|-");

        List<String> valuesWithMetrics = new ArrayList<>();
        for (String string : inputValues) {
            valuesWithMetrics.add(string.trim());
        }
        return valuesWithMetrics;
    }

    private static List<String> getInputMetrics(List<String> valuesWithMetrics) {
        List<String> inputMetrics = new ArrayList<>();
        for (String s : valuesWithMetrics) {
            String[] split = s.split(" ");
            String metric = split[1];
            inputMetrics.add(metric);
        }
        return inputMetrics;
    }

    private static List<Double> getInputNumbers(List<String> valuesWithMetrics) {
        List<Double> inputNumbers = new ArrayList<>();
        for (String s : valuesWithMetrics) {
            String[] split = s.split(" ");
            Double number = Double.parseDouble(split[0]);
            inputNumbers.add(number);
        }
        return inputNumbers;
    }

    private static int getMinimumMetricIndex(List<String> inputMetrics) {
        int minScaleIndex = METRIC_ORDER.length;
        for (String metric : inputMetrics) {
            for (int index = 0; index < METRIC_ORDER.length; index++) {
                if (metric.equals(METRIC_ORDER[index])) {
                    if (minScaleIndex > index) {
                        minScaleIndex = index;
                    }
                    break;
                }
            }
        }
        return minScaleIndex;
    }

    private static double calculateResult(List<Character> inputOperators, List<Double> inputNumbersConverted) {
        int numbersIndex = 0;
        double result = inputNumbersConverted.get(numbersIndex);
        numbersIndex++;
        for (Character operator : inputOperators) {
            if (operator == '+') {
                result += inputNumbersConverted.get(numbersIndex);
            } else {
                result -= inputNumbersConverted.get(numbersIndex);
            }
            numbersIndex++;
        }
        return result;
    }

    private static List<Double> getNumbersConvertedToMetricScale(List<String> inputMetrics,
                                                                 List<Double> inputNumbers, double[] metricScale) {
        List<Double> inputNumbersConverted = new ArrayList<>();
        for (int index = 0; index < inputNumbers.size(); index++) {
            int metricIndex = getMetricIndex(inputMetrics.get(index));
            inputNumbersConverted.add(inputNumbers.get(index) * metricScale[metricIndex]);
        }
        return inputNumbersConverted;
    }

    private static int getMetricIndex(String metric) {
        for (int i = 0; i < METRIC_ORDER.length; i++) {
            if (METRIC_ORDER[i].equals(metric)) {
                return i;
            }
        }
        return -1;
    }

    private static double[] getMetricScale(int minScaleIndex) {
        switch (minScaleIndex) {
            case MM_INDEX:
                return MM_SCALING;
            case CM_INDEX:
                return CM_SCALING;
            case DM_INDEX:
                return DM_SCALING;
            case M_INDEX:
                return M_SCALING;
            case KM_INDEX:
                return KM_SCALING;
            default:
                return new double[]{};
        }
    }
}
