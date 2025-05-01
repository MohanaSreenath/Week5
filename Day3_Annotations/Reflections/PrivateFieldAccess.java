import java.lang.reflect.*;

class Person {
    private int age = 25;
}

public class PrivateFieldAccess {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Field field = person.getClass().getDeclaredField("age");
        field.setAccessible(true);
        
        System.out.println("Original Age: " + field.get(person));
        field.set(person, 30);
        System.out.println("Modified Age: " + field.get(person));
    }
}