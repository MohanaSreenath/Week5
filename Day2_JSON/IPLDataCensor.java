import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import java.io.*;
import java.util.*;

public class IPLDataCensor {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        String jsonInputFile = "ipl_matches.json";
        String jsonOutputFile = "censored_ipl_matches.json";
        String csvInputFile = "ipl_matches.csv";
        String csvOutputFile = "censored_ipl_matches.csv";

        processJsonData(jsonInputFile, jsonOutputFile);
        processCsvData(csvInputFile, csvOutputFile);
    }

    //  Process JSON Data and Apply Censorship
    public static void processJsonData(String inputFile, String outputFile) throws IOException {
        JsonNode jsonData = objectMapper.readTree(new File(inputFile));
        ArrayNode updatedData = objectMapper.createArrayNode();

        jsonData.forEach(match -> {
            ObjectNode matchNode = match.deepCopy();
            matchNode.put("team1", censorTeam(match.get("team1").asText()));
            matchNode.put("team2", censorTeam(match.get("team2").asText()));
            matchNode.put("winner", censorTeam(match.get("winner").asText()));
            matchNode.put("player_of_match", "REDACTED");
            updatedData.add(matchNode);
        });

        objectMapper.writeValue(new File(outputFile), updatedData);
        System.out.println("Censored JSON data saved to " + outputFile);
    }

    // 2️⃣ Process CSV Data and Apply Censorship
    public static void processCsvData(String inputFile, String outputFile) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            lines.add(reader.readLine()); // Preserve header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                data[1] = censorTeam(data[1]); // Mask Team1
                data[2] = censorTeam(data[2]); // Mask Team2
                data[5] = censorTeam(data[5]); // Mask Winner
                data[6] = "REDACTED"; // Redact Player of the Match
                lines.add(String.join(",", data));
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }

        System.out.println("Censored CSV data saved to " + outputFile);
    }

    // 3️⃣ Mask Team Names (Example: "Mumbai Indians" → "Mumbai ***")
    private static String censorTeam(String teamName) {
        if (teamName.contains(" ")) {
            return teamName.substring(0, teamName.indexOf(" ")) + " ***";
        }
        return teamName;
    }
}