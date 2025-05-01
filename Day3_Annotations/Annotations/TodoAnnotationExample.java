package Annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

class Project {
    @Todo(task = "Implement login feature", assignedTo = "Mohana", priority = "HIGH")
    void loginFeature() {
        System.out.println("Login feature development in progress...");
    }

    @Todo(task = "Optimize database queries", assignedTo = "Mohana")
    void optimizeDatabase() {
        System.out.println("Optimizing database queries...");
    }
}

public class TodoAnnotationExample {
    public static void main(String[] args) throws Exception {
        Project project = new Project();
        Method[] methods = project.getClass().getDeclaredMethods();

        System.out.println("Pending Tasks:");
        for (Method method : methods) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo annotation = method.getAnnotation(Todo.class);
                System.out.println("Task: " + annotation.task() + " | Assigned To: " + annotation.assignedTo() + " | Priority: " + annotation.priority());
            }
        }

        project.loginFeature();
        project.optimizeDatabase();
    }
}