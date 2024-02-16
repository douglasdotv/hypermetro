package br.com.dv.metro;

import br.com.dv.metro.exception.FileReadException;
import br.com.dv.metro.metrosystem.MetroSystem;
import br.com.dv.metro.metrosystem.model.StationDTO;
import br.com.dv.metro.util.FileReader;
import br.com.dv.metro.util.JsonParser;
import br.com.dv.metro.util.MetroOutput;
import com.google.gson.JsonParseException;

import java.util.Map;

public class Metro {

    private static final String USAGE = "Usage: java -jar metro.jar <file_path>";

    public static void main(String[] args) {
        if (args.length != 1) {
            MetroOutput.output(USAGE);
            return;
        }

        try {
            String metroLinesFilePath = args[0];
            String metroLinesJsonStr = FileReader.readFileAsString(metroLinesFilePath);
            Map<String, Map<String, StationDTO>> metroLines = JsonParser.parseJsonToMap(metroLinesJsonStr);
            MetroSystem metroSystem = new MetroSystem(metroLines);
            metroSystem.run();
        } catch (FileReadException | JsonParseException ex) {
            MetroOutput.outputErr(ex.getMessage());
        }
    }

}
