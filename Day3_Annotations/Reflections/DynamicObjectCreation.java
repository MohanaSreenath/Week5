import java.lang.reflect.*;

class Student {
    String name;
    Student() {
        name = "Default Student";
    }
}

public class DynamicObjectCreation {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("Student");
        Object obj = clazz.getDeclaredConstructor().newInstance();
        
        System.out.println("Created Object: " + obj.getClass().getName());
    }
}