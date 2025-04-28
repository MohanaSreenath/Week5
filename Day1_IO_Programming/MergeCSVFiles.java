import java.io.*;
import java.util.*;

public class MergeCSVFiles {
    public static void main(String[] args) throws IOException {
        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String outputFile = "merged_students.csv";

        Map<Integer, String> studentMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                studentMap.put(Integer.parseInt(data[0]), line);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                studentMap.put(Integer.parseInt(data[0]), studentMap.get(Integer.parseInt(data[0])) + "," + data[1] + "," + data[2]);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("ID,Name,Age,Marks,Grade\n");
            studentMap.values().forEach(student -> {
                try {
                    writer.write(student + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("Merged CSV file created successfully.");
    }
}