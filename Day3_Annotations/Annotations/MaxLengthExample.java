package Annotations;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MaxLength {
    int value();
}

class User {
    @MaxLength(10)
    private String username;

    User(String username) {
        if (username.length() > User.class.getDeclaredFields()[0].getAnnotation(MaxLength.class).value()) {
            throw new IllegalArgumentException("Username exceeds max length!");
        }
        this.username = username;
        System.out.println("User created successfully: " + this.username);
    }
}

public class MaxLengthExample {
    public static void main(String[] args) {
        new User("Mohana123"); // Valid
        new User("VeryLongUsername"); // Throws Exception
    }
}