package Annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.HashMap;

@Retention(RetentionPolicy.RUNTIME)
@interface CacheResult {}

class ExpensiveOperations {
    private static final HashMap<Integer, Integer> cache = new HashMap<>();

    @CacheResult
    int expensiveCalculation(int num) {
        if (cache.containsKey(num)) {
            System.out.println("Returning cached result...");
            return cache.get(num);
        }
        int result = num * num;
        cache.put(num, result);
        System.out.println("Computed result: " + result);
        return result;
    }
}

public class CachingSystem {
    public static void main(String[] args) throws Exception {
        ExpensiveOperations operations = new ExpensiveOperations();
        System.out.println(operations.expensiveCalculation(5));
        System.out.println(operations.expensiveCalculation(5)); // Uses cache
    }
}