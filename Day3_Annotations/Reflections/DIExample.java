import java.lang.annotation.*;
import java.lang.reflect.*;

// Step 1: Define the @Inject Annotation
@Retention(RetentionPolicy.RUNTIME)
@interface Inject {}

// Step 2: Create a Dependency (DatabaseService)
class DatabaseService {
    public void connect() {
        System.out.println("Connected to Database!");
    }
}

// Step 3: Define a Class that Requires Dependency Injection
class Application {
    @Inject
    private DatabaseService databaseService;

    public void run() {
        databaseService.connect();
        System.out.println("Application is running!");
    }
}

// Step 4: Implement the DI Container for Dependency Injection
class DependencyInjector {
    public static void injectDependencies(Object obj) throws Exception {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object instance = field.getType().getDeclaredConstructor().newInstance();
                field.set(obj, instance);
            }
        }
    }
}

// Step 5: Demonstrate Dependency Injection in Action
public class DIExample {
    public static void main(String[] args) throws Exception {
        Application app = new Application();
        DependencyInjector.injectDependencies(app); // Inject dependencies dynamically
        app.run(); // Run the application with injected dependencies
    }
}
