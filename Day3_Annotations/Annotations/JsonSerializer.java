package Annotations;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.HashMap;

@Retention(RetentionPolicy.RUNTIME)
@interface JsonField {
    String name();
}

class User {
    @JsonField(name = "user_name")
    String username;

    @JsonField(name = "email_id")
    String email;

    User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}

public class JsonSerializer {
    public static String toJson(Object obj) throws Exception {
        HashMap<String, String> jsonMap = new HashMap<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                jsonMap.put(field.getAnnotation(JsonField.class).name(), (String) field.get(obj));
            }
        }
        return jsonMap.toString().replace("=", ": "); // Simulated JSON format
    }

    public static void main(String[] args) throws Exception {
        User user = new User("Mohana", "mohana@example.com");
        System.out.println(toJson(user));
    }
}