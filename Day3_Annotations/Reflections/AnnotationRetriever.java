import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
}

@Author(name = "Mohana")
class MyClass {}

public class AnnotationRetriever {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = MyClass.class;
        Author author = clazz.getAnnotation(Author.class);
        
        System.out.println("Author: " + author.name());
    }
}