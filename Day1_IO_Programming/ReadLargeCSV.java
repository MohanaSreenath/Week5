import java.io.*;

public class ReadLargeCSV {
    public static void main(String[] args) throws IOException {
        String fileName = "large_data.csv";
        int chunkSize = 100;
        int processed = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processed++;
                if (processed % chunkSize == 0) {
                    System.out.println("Processed " + processed + " records...");
                }
            }
        }

        System.out.println("Total records processed: " + processed);
    }
}