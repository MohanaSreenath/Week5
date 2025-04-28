import java.io.*;
import java.util.*;

public class SortCSVRecords {
    public static void main(String[] args) throws IOException {
        String fileName = "employees.csv";
        List<String[]> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String header = reader.readLine();
            employees.add(header.split(","));
            String line;
            while ((line = reader.readLine()) != null) {
                employees.add(line.split(","));
            }
        }

        employees.stream().skip(1)
                .sorted((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])))
                .limit(5)
                .forEach(e -> System.out.println(String.join(",", e)));
    }
}