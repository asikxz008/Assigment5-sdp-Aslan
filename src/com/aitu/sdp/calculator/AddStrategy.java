package com.aitu.sdp.calculator;

public final class AddStrategy implements CalculationStrategy {
    @Override
    public double calculate(double left, double right) {
        return left + right;
    }
}
