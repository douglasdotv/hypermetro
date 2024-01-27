package br.com.dv.metro;

import java.util.List;
import java.util.Map;

public class OutputStrategy implements CommandStrategy {

    private static final int MIN_INPUT_SIZE = 2;
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command";
    private static final String LINE_DOES_NOT_EXIST_TEMPLATE = "Line %s does not exist in the metro system%n";

    @Override
    public void execute(List<String> input, Map<String, MetroLine> metroLines) {
        if (input.size() < MIN_INPUT_SIZE) {
            System.out.println(INVALID_COMMAND_MESSAGE);
            return;
        }

        String lineName = input.get(1).toLowerCase();

        if (!metroLines.containsKey(lineName)) {
            System.out.printf(LINE_DOES_NOT_EXIST_TEMPLATE, lineName);
            return;
        }

        MetroLine line = metroLines.get(lineName);
        line.outputStations();
    }

}
