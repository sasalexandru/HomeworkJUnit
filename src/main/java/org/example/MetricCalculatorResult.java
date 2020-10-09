package org.example;

import java.util.Objects;

public class MetricCalculatorResult {
    private Double value;
    private String metric;

    public MetricCalculatorResult(Double value, String metric) {
        this.value = value;
        this.metric = metric;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetricCalculatorResult that = (MetricCalculatorResult) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(metric, that.metric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, metric);
    }

    @Override
    public String toString() {
        return "MetricCalculatorResult{" +
                "value=" + value +
                ", metric='" + metric + '\'' +
                '}';
    }
}
