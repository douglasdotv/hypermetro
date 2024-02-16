package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.exception.MetroLineNotFoundException;
import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;

import java.util.List;
import java.util.Map;

public abstract class AbstractMetroLineUpdateStrategy implements CommandStrategy {

    private static final int MIN_INPUT_SIZE = 3;
    private static final int TIME_ARG_INDEX = 3;
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command";

    @Override
    public void execute(List<String> input, Map<String, MetroLine> metroLines, MetroGraph graph) {
        if (input.size() < MIN_INPUT_SIZE) {
            System.out.println(INVALID_COMMAND_MESSAGE);
            return;
        }

        String lineName = input.get(1).toLowerCase();
        String stationName = input.get(2);
        int time = input.size() > TIME_ARG_INDEX ? Integer.parseInt(input.get(TIME_ARG_INDEX)) : 0;

        if (!metroLines.containsKey(lineName)) {
            throw new MetroLineNotFoundException(lineName);
        }

        MetroLine line = metroLines.get(lineName);
        doAction(graph, line, stationName,time);
    }

    protected abstract void doAction(MetroGraph graph, MetroLine line, String stationName, int time);

}
