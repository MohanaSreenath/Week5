import java.io.*;

public class DatabaseToCSV {
    public static void main(String[] args) throws IOException {
        String fileName = "employee_report.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Employee ID,Name,Department,Salary\n");
            writer.write("301,Ravi Kumar,Engineering,78000\n");
            writer.write("302,Sai Krishna,Finance,85000\n");
            writer.write("303,Lakshmi Priya,HR,65000\n");
            writer.write("304,Kiran Reddy,IT,92000\n");
            writer.write("305,Anusha Rao,Marketing,88000\n");
        }

        System.out.println("CSV Report generated successfully.");
    }
}