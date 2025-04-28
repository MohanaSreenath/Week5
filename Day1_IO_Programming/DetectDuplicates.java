import java.io.*;
import java.util.*;

public class DetectDuplicates {
    public static void main(String[] args) throws IOException {
        String fileName = "students.csv";
        Set<String> ids = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String id = line.split(",")[0];
                if (!ids.add(id)) {
                    duplicates.add(line);
                }
            }
        }

        System.out.println("Duplicate Records:");
        duplicates.forEach(System.out::println);
    }
}