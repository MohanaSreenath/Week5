import java.io.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptDecryptCSV {
    private static final String KEY = "1234567812345678"; // 16-byte AES key

    public static void main(String[] args) throws Exception {
        String inputFile = "sensitive_data.csv";
        String encryptedFile = "encrypted_data.csv";
        String decryptedFile = "decrypted_data.csv";

        encryptCSV(inputFile, encryptedFile);
        decryptCSV(encryptedFile, decryptedFile);
    }

    public static void encryptCSV(String inputFile, String outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey key = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            writer.write(reader.readLine()); // Write header
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                data[2] = Base64.getEncoder().encodeToString(cipher.doFinal(data[2].getBytes())); // Encrypt salary
                data[3] = Base64.getEncoder().encodeToString(cipher.doFinal(data[3].getBytes())); // Encrypt email
                writer.write(String.join(",", data));
                writer.newLine();
            }
        }
        System.out.println("CSV Data Encrypted Successfully!");
    }

    public static void decryptCSV(String inputFile, String outputFile) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKey key = new SecretKeySpec(KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            writer.write(reader.readLine()); // Write header
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                data[2] = new String(cipher.doFinal(Base64.getDecoder().decode(data[2]))); // Decrypt salary
                data[3] = new String(cipher.doFinal(Base64.getDecoder().decode(data[3]))); // Decrypt email
                writer.write(String.join(",", data));
                writer.newLine();
            }
        }
        System.out.println("CSV Data Decrypted Successfully!");
    }
}