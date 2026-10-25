package com.aitu.sdp.calculator;

public final class MultiplyStrategy implements CalculationStrategy {
    @Override
    public double calculate(double left, double right) {
        return left * right;
    }
}
