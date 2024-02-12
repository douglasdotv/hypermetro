package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;
import br.com.dv.metro.metrosystem.model.Station;

public class RemoveStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroGraph graph, MetroLine line, String stationName) {
        Station station = line.remove(stationName).getStation();
        graph.removeStation(station);
    }

}
