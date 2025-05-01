import java.lang.reflect.*;

class TestClass {
    void testMethod() {
        try { Thread.sleep(200); } catch (InterruptedException e) { }
        System.out.println("Test method executed.");
    }
}

public class MethodTiming {
    public static void main(String[] args) throws Exception {
        TestClass obj = new TestClass();
        Method method = obj.getClass().getMethod("testMethod");

        long start = System.nanoTime();
        method.invoke(obj);
        long end = System.nanoTime();

        System.out.println("Execution time: " + (end - start) / 1_000_000 + " ms");
    }
}