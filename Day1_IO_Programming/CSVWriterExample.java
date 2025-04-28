import java.io.*;
import java.util.List;

class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String toCSV() {
        return id + "," + name + "," + department + "," + salary;
    }
}

public class CSVWriterExample {
    public static void main(String[] args) {
        String fileName = "employees.csv";
        List<Employee> employees = List.of(
                new Employee(201, "Venkatesh Rao", "Engineering", 95000),
                new Employee(202, "Sai Ram", "Finance", 87000),
                new Employee(203, "Lakshmi Prasad", "HR", 72000),
                new Employee(204, "Krishna Teja", "Marketing", 82000),
                new Employee(205, "Anusha Reddy", "IT", 98000)
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("ID,Name,Department,Salary");
            writer.newLine();
            for (Employee emp : employees) {
                writer.write(emp.toCSV());
                writer.newLine();
            }
            System.out.println("CSV file created successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}