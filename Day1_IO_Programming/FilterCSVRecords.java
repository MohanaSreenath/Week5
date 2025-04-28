import java.io.*;
import java.util.*;

public class FilterCSVRecords {
    public static void main(String[] args) {
        String fileName = "students.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (Double.parseDouble(data[3]) > 80) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}