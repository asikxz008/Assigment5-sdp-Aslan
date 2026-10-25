import com.aitu.sdp.calculator.AddStrategy;
import com.aitu.sdp.calculator.Calculator;
import com.aitu.sdp.calculator.SubtractStrategy;

public final class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AddStrategy());
        System.out.println("Add: 12 + 4 = " + calculator.calculate(12, 4));

        calculator.setStrategy(new SubtractStrategy());
        System.out.println("Subtract: 12 - 4 = " + calculator.calculate(12, 4));
    }
}
