import java.lang.reflect.*;

class MathOperations {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int multiply(int a, int b) { return a * b; }
}

public class DynamicMethodCaller {
    public static void main(String[] args) throws Exception {
        MathOperations operations = new MathOperations();
        Method method = operations.getClass().getMethod("add", int.class, int.class);
        
        int result = (int) method.invoke(operations, 10, 5);
        System.out.println("Result of add(10, 5): " + result);
    }
}