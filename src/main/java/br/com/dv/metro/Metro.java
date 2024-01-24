package br.com.dv.metro;

import java.util.List;

public class Metro {

    private static final String USAGE = "Usage: java -jar metro.jar <file_path>";

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(USAGE);
            return;
        }

        try {
            String stationsFilePath = args[0];
            List<String> stationNames = FileReader.readFileLinesToList(stationsFilePath);

            MetroLine metroLine = new MetroLine();
            stationNames.forEach(metroLine::addStation);
            metroLine.outputStations();
        } catch (FileReadException e) {
            System.err.println(e.getMessage());
        }
    }

}
