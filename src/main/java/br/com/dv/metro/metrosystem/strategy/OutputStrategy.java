package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.exception.MetroLineNotFoundException;
import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;

import java.util.List;
import java.util.Map;

public class OutputStrategy implements CommandStrategy {

    private static final int MIN_INPUT_SIZE = 2;
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command";

    @Override
    public void execute(List<String> input, Map<String, MetroLine> metroLines, MetroGraph graph) {
        if (input.size() < MIN_INPUT_SIZE) {
            System.out.println(INVALID_COMMAND_MESSAGE);
            return;
        }

        String lineName = input.get(1).toLowerCase();

        if (!metroLines.containsKey(lineName)) {
            throw new MetroLineNotFoundException(lineName);
        }

        MetroLine line = metroLines.get(lineName);
        line.outputStations();
    }

}
