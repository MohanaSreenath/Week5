import java.lang.reflect.*;

class Product {
    String name = "Laptop";
    int price = 1000;
}

public class JsonGenerator {
    public static String toJson(Object obj) throws Exception {
        StringBuilder json = new StringBuilder("{");
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            json.append("\"").append(field.getName()).append("\": ")
                .append("\"").append(field.get(obj)).append("\", ");
        }
        return json.toString().replaceAll(", $", " }");
    }

    public static void main(String[] args) throws Exception {
        Product product = new Product();
        System.out.println(toJson(product));
    }
}