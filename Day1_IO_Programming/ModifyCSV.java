import java.io.*;
import java.util.*;

public class ModifyCSV {
    public static void main(String[] args) throws IOException {
        String inputFile = "employees.csv";
        String outputFile = "updated_employees.csv";

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            lines.add(reader.readLine());
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[2].equalsIgnoreCase("IT")) {
                    data[3] = String.valueOf(Double.parseDouble(data[3]) * 1.1);
                }
                lines.add(String.join(",", data));
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
        System.out.println("Updated salaries for IT department employees.");
    }
}