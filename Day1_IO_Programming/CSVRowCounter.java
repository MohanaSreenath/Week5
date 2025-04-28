import java.io.*;

public class CSVRowCounter {
    public static void main(String[] args) {
        String fileName = "students.csv";
        int rowCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            while (reader.readLine() != null) {
                rowCount++;
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Total Records (excluding header): " + rowCount);
    }
}