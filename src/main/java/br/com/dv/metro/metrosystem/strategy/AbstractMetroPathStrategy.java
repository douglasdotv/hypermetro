package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.exception.MetroLineNotFoundException;
import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;
import br.com.dv.metro.metrosystem.model.Station;

import java.util.List;
import java.util.Map;

public abstract class AbstractMetroPathStrategy implements CommandStrategy {

    private static final int MIN_INPUT_SIZE = 4;
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command";

    @Override
    public void execute(List<String> input, Map<String, MetroLine> metroLines, MetroGraph graph) {
        if (input.size() < MIN_INPUT_SIZE) {
            System.out.println(INVALID_COMMAND_MESSAGE);
            return;
        }

        String lineName1 = input.get(1).toLowerCase();
        String stationName1 = input.get(2);
        String lineName2 = input.get(3).toLowerCase();
        String stationName2 = input.get(4);

        if (!metroLines.containsKey(lineName1)) {
            throw new MetroLineNotFoundException(lineName1);
        }
        if (!metroLines.containsKey(lineName2)) {
            throw new MetroLineNotFoundException(lineName2);
        }

        MetroLine line1 = metroLines.get(lineName1);
        MetroLine line2 = metroLines.get(lineName2);

        Station station1 = line1.getStation(stationName1);
        Station station2 = line2.getStation(stationName2);

        doAction(graph, station1, station2);
    }

    protected abstract void doAction(MetroGraph graph, Station station1, Station station2);

}
