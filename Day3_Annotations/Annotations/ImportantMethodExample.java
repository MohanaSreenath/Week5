package Annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface ImportantMethod {
    String level() default "HIGH";
}

class Feature {
    @ImportantMethod(level = "CRITICAL")
    void criticalOperation() {
        System.out.println("Executing a critical operation!");
    }

    @ImportantMethod
    void regularOperation() {
        System.out.println("Executing a regular important operation!");
    }
}

public class ImportantMethodExample {
    public static void main(String[] args) throws Exception {
        Feature feature = new Feature();
        Method[] methods = feature.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName() + " | Importance Level: " + annotation.level());
            }
        }

        feature.criticalOperation();
        feature.regularOperation();
    }
}