import java.io.*;
import com.google.gson.*;

public class JSONToCSV {
    public static void main(String[] args) throws IOException {
        String jsonFile = "students.json";
        String csvFile = "converted_students.csv";
        Gson gson = new Gson();
        Student[] students = gson.fromJson(new FileReader(jsonFile), Student[].class);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write("ID,Name,Age,Marks\n");
            for (Student s : students) {
                writer.write(s.id + "," + s.name + "," + s.age + "," + s.marks + "\n");
            }
        }

        System.out.println("Converted JSON to CSV successfully.");
    }
}