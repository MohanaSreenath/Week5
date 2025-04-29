import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class User {
    public String name;
    public int age;
    public String email;

    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}

public class JsonHandsOnPractice {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        // 1️⃣ Read a JSON file and print all keys and values
        JsonNode jsonNode = objectMapper.readTree(new File("data.json"));
        jsonNode.fields().forEachRemaining(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // 2️⃣ Convert a list of Java objects into a JSON array
        List<User> users = List.of(
                new User("Sai Teja", 28, "sai@example.com"),
                new User("Anusha", 24, "anusha@example.com"),
                new User("Krishna", 30, "krishna@example.com")
        );
        String usersJson = objectMapper.writeValueAsString(users);
        System.out.println("\nUsers JSON Array: " + usersJson);

        // 3️⃣ Filter JSON data: Print only users older than 25 years
        List<User> filteredUsers = users.stream()
                .filter(user -> user.age > 25)
                .collect(Collectors.toList());
        System.out.println("\nFiltered Users:");
        filteredUsers.forEach(u -> System.out.println(u.name + " - Age: " + u.age));

        // 4️⃣ Validate an email field using JSON Schema (Simple regex check)
        users.forEach(user -> {
            if (!user.email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("\nInvalid Email: " + user.email);
            }
        });

        // 5️⃣ Merge two JSON files into a single JSON object
        ObjectNode json1 = objectMapper.readTree(new File("file1.json")).deepCopy();
        ObjectNode json2 = objectMapper.readTree(new File("file2.json")).deepCopy();
        json1.setAll(json2);
        System.out.println("\nMerged JSON: " + json1);

        // 6️⃣ Convert JSON to XML format
        XmlMapper xmlMapper = new XmlMapper();
        String xmlData = xmlMapper.writeValueAsString(jsonNode);
        System.out.println("\nConverted XML:\n" + xmlData);

        // 7️⃣ Convert CSV data into JSON
        List<Map<String, String>> csvData = readCSV("students.csv");
        String csvJson = objectMapper.writeValueAsString(csvData);
        System.out.println("\nCSV to JSON:\n" + csvJson);

        // 8️⃣ Generate a JSON report from database records (Simulated)
        Map<String, Object> report = new HashMap<>();
        report.put("ReportDate", new Date().toString());
        report.put("EmployeeCount", 50);
        report.put("Department", "IT");
        String reportJson = objectMapper.writeValueAsString(report);
        System.out.println("\nGenerated JSON Report:\n" + reportJson);
    }

    public static List<Map<String, String>> readCSV(String fileName) throws IOException {
        List<Map<String, String>> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String[] headers = reader.readLine().split(",");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> record = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    record.put(headers[i], values[i]);
                }
                records.add(record);
            }
        }
        return records;
    }
}