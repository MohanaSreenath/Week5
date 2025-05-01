import java.lang.reflect.*;

class Calculator {
    private int multiply(int a, int b) {
        return a * b;
    }
}

public class PrivateMethodInvoker {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();
        Method method = calculator.getClass().getDeclaredMethod("multiply", int.class, int.class);
        method.setAccessible(true);
        
        int result = (int) method.invoke(calculator, 5, 4);
        System.out.println("Result of multiply(5, 4): " + result);
    }
}