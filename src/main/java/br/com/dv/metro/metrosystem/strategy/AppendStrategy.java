package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;
import br.com.dv.metro.metrosystem.model.Station;

import java.util.ArrayList;

public class AppendStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroGraph graph, MetroLine line, String stationName) {
        Station station = new Station(stationName, line, new ArrayList<>());
        Station lastStation = line.getLastStation();
        graph.addEdge(lastStation, station, false);
        line.append(station);
    }

}
