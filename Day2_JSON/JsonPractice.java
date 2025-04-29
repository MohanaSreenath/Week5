import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.*;
import java.util.*;

class Student {
    public String name;
    public int age;
    public List<String> subjects;

    public Student(String name, int age, List<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }
}

class Car {
    public String brand;
    public String model;
    public int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}

public class JsonPractice {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        // 1️⃣ Create a JSON object for a Student
        ObjectNode studentJson = objectMapper.createObjectNode();
        studentJson.put("name", "Sai Teja");
        studentJson.put("age", 21);
        studentJson.set("subjects", objectMapper.valueToTree(List.of("Math", "Physics", "Chemistry")));
        System.out.println("Student JSON: " + studentJson);

        // 2️⃣ Convert a Java object (Car) into JSON format
        Car car = new Car("Toyota", "Camry", 2023);
        String carJson = objectMapper.writeValueAsString(car);
        System.out.println("\nCar JSON: " + carJson);

        // 3️⃣ Read a JSON file and extract specific fields (name, email)
        JsonNode usersJson = objectMapper.readTree(new File("users.json"));
        usersJson.forEach(user -> System.out.println("Name: " + user.get("name") + ", Email: " + user.get("email")));

        // 4️⃣ Merge two JSON objects into one
        ObjectNode json1 = objectMapper.createObjectNode().put("name", "Venkatesh Rao").put("age", 30);
        ObjectNode json2 = objectMapper.createObjectNode().put("city", "Hyderabad").put("country", "India");
        json1.setAll(json2);
        System.out.println("\nMerged JSON: " + json1);

        // 5️⃣ Validate JSON structure using Jackson
        try {
            objectMapper.readTree("{ \"name\": \"Lakshmi\", \"age\": \"twenty-five\" }");
            System.out.println("\nValid JSON format!");
        } catch (IOException e) {
            System.out.println("\nInvalid JSON format!");
        }

        // 6️⃣ Convert a list of Java objects into a JSON array
        List<Student> students = List.of(
                new Student("Krishna", 22, List.of("Biology", "Math")),
                new Student("Anusha", 19, List.of("History", "Literature"))
        );
        String studentsJson = objectMapper.writeValueAsString(students);
        System.out.println("\nStudents JSON Array: " + studentsJson);

        // 7️⃣ Parse JSON and filter only those records where age > 25
        List<Map<String, Object>> people = objectMapper.readValue(new File("people.json"), new TypeReference<>() {});
        people.stream()
                .filter(person -> (int) person.get("age") > 25)
                .forEach(System.out::println);
    }
}