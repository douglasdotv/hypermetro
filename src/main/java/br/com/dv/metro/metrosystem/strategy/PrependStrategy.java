package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;
import br.com.dv.metro.metrosystem.model.Station;

import java.util.ArrayList;

public class PrependStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroGraph graph, MetroLine line, String stationName, int time) {
        Station station = new Station(stationName, line, new ArrayList<>(), time);
        Station firstStation = line.getFirstStation();
        graph.addEdge(station, firstStation, false);
        line.prepend(station);
    }

}
