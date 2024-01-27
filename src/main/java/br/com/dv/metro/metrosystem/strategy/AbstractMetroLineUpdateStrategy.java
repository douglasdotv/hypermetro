package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroLine;

import java.util.List;
import java.util.Map;

public abstract class AbstractMetroLineUpdateStrategy implements CommandStrategy {

    private static final int MIN_INPUT_SIZE = 3;
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command";
    private static final String LINE_DOES_NOT_EXIST_TEMPLATE = "Line %s does not exist in the metro system%n";

    @Override
    public void execute(List<String> input, Map<String, MetroLine> metroLines) {
        if (input.size() < MIN_INPUT_SIZE) {
            System.out.println(INVALID_COMMAND_MESSAGE);
            return;
        }

        String lineName = input.get(1).toLowerCase();
        String stationName = input.get(2);

        if (!metroLines.containsKey(lineName)) {
            System.out.printf(LINE_DOES_NOT_EXIST_TEMPLATE, lineName);
            return;
        }

        MetroLine line = metroLines.get(lineName);
        doAction(line, stationName);
    }

    protected abstract void doAction(MetroLine line, String stationName);

}