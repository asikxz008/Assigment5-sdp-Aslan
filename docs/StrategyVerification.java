import com.aitu.sdp.calculator.*;

public final class StrategyVerification {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AddStrategy());
        Calculator original = calculator;
        double[] actual = new double[11];
        actual[0] = calculator.calculate(12, 4);
        actual[1] = calculator.calculate(-3, 0);
        actual[2] = calculator.calculate(0.5, 0.25);
        calculator.setStrategy(new SubtractStrategy());
        actual[3] = calculator.calculate(12, 4);
        actual[4] = calculator.calculate(4, 12);
        actual[5] = calculator.calculate(0.5, 0.25);
        calculator.setStrategy(new MultiplyStrategy());
        actual[6] = calculator.calculate(12, 4);
        actual[7] = calculator.calculate(-3, 4);
        actual[8] = calculator.calculate(0, 12);
        actual[9] = calculator.calculate(0.5, 0.25);
        calculator.setStrategy(new AddStrategy());
        actual[10] = calculator.calculate(12, 4);
        double[] expected = {16, -3, 0.75, 8, -8, 0.25, 48, -12, 0, 0.125, 16};
        String[] labels = {"addition", "addition with negative and zero", "fractional addition",
            "subtraction", "subtraction operand order", "fractional subtraction",
            "multiplication", "negative multiplication", "zero multiplication",
            "fractional multiplication", "switch back to addition"};
        for (int i = 0; i < expected.length; i++) {
            if (actual[i] != expected[i]) {
                throw new AssertionError(labels[i] + ": " + actual[i]);
            }
            System.out.println("T" + (i + 1) + " PASS | " + labels[i] + " = " + actual[i]);
        }
        if (calculator != original) {
            throw new AssertionError("Context instance changed");
        }
        System.out.println("T12 PASS | same Calculator instance across switches");
        try {
            new Calculator(null);
            throw new AssertionError("Constructor accepted null");
        } catch (NullPointerException expectedException) {
            System.out.println("T13 PASS | constructor rejects null");
        }
        try {
            calculator.setStrategy(null);
            throw new AssertionError("Setter accepted null");
        } catch (NullPointerException expectedException) {
            if (calculator.calculate(12, 4) != 16) {
                throw new AssertionError("Failed setter changed active strategy");
            }
            System.out.println("T14 PASS | null replacement rejected; old strategy retained");
        }
        CalculationStrategy differenceOfSquares = new CalculationStrategy() {
            @Override
            public double calculate(double left, double right) {
                return left * left - right * right;
            }
        };
        calculator.setStrategy(differenceOfSquares);
        if (calculator.calculate(5, 3) != 16) {
            throw new AssertionError("Context rejected an independent implementation");
        }
        System.out.println("T15 PASS | independent implementation works without context changes");
        System.out.println("SUMMARY: 15/15 PASS");
    }
}
