import java.io.*;

public class SearchCSVRecord {
    public static void main(String[] args) {
        String fileName = "employees.csv";
        String searchName = "Sai Ram";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[1].equalsIgnoreCase(searchName)) {
                    System.out.println("Department: " + data[2] + ", Salary: $" + data[3]);
                    return;
                }
            }
            System.out.println("Employee not found!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}