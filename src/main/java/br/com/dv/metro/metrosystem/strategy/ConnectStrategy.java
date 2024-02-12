package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;
import br.com.dv.metro.metrosystem.model.Station;

public class ConnectStrategy extends AbstractMetroPathStrategy {

    @Override
    protected void doAction(MetroGraph graph, Station station1, Station station2) {
        graph.addEdge(station1, station2, true);

        MetroLine line1 = station1.line();
        String stationName1 = station1.name();
        MetroLine line2 = station2.line();
        String stationName2 = station2.name();

        line1.connect(stationName1, line2, stationName2);
    }

}
