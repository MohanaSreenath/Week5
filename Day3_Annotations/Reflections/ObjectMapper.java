import java.lang.reflect.*;
import java.util.*;

class User {
    String username;
    int age;
}

public class ObjectMapper {
    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (properties.containsKey(field.getName())) {
                field.set(obj, properties.get(field.getName()));
            }
        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> data = Map.of("username", "Mohana", "age", 25);
        User user = toObject(User.class, data);
        System.out.println("Created User: " + user.username + ", Age: " + user.age);
    }
}