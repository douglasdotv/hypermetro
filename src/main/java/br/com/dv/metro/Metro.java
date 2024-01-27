package br.com.dv.metro;

import com.google.gson.JsonParseException;

import java.util.Map;

public class Metro {

    private static final String USAGE = "Usage: java -jar metro.jar <file_path>";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(USAGE);
            return;
        }

        try {
            String metroLinesFilePath = args[0];
            String metroLinesJsonStr = FileReader.readFileAsString(metroLinesFilePath);
            Map<String, Map<String, String>> metroLines = JsonParser.parseJsonToMap(metroLinesJsonStr);
            MetroSystem metroSystem = new MetroSystem(metroLines);
            metroSystem.run();
        } catch (FileReadException | JsonParseException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
