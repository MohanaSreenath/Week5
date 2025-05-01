package Annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface LogExecutionTime {}

class PerformanceTest {
    @LogExecutionTime
    void fastMethod() {
        try { Thread.sleep(100); } catch (InterruptedException e) { }
        System.out.println("Fast method executed.");
    }

    @LogExecutionTime
    void slowMethod() {
        try { Thread.sleep(500); } catch (InterruptedException e) { }
        System.out.println("Slow method executed.");
    }
}

public class ExecutionTimeLogger {
    public static void main(String[] args) throws Exception {
        PerformanceTest test = new PerformanceTest();
        Method[] methods = test.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long start = System.nanoTime();
                method.invoke(test);
                long end = System.nanoTime();
                System.out.println("Execution time of " + method.getName() + ": " + (end - start) / 1_000_000 + " ms");
            }
        }
    }
}