package Annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface RoleAllowed {
    String value();
}

class SecureOperations {
    @RoleAllowed("ADMIN")
    void adminTask() {
        System.out.println("Admin task executed!");
    }
}

public class RoleAccessControl {
    public static void main(String[] args) throws Exception {
        SecureOperations operations = new SecureOperations();
        String currentUserRole = "USER"; // Simulating user role

        Method method = operations.getClass().getDeclaredMethod("adminTask");
        RoleAllowed annotation = method.getAnnotation(RoleAllowed.class);

        if (annotation.value().equals(currentUserRole)) {
            method.invoke(operations);
        } else {
            System.out.println("Access Denied!");
        }
    }
}