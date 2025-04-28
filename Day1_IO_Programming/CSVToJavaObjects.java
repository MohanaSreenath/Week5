import java.io.*;
import java.util.*;

class Student {
    int id;
    String name;
    int age;
    double marks;

    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age + " | Marks: " + marks;
    }
}

public class CSVToJavaObjects {
    public static void main(String[] args) {
        String fileName = "students.csv";
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Double.parseDouble(data[3])));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        students.forEach(System.out::println);
    }
}