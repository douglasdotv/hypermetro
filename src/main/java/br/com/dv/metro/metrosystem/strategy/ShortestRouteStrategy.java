package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.model.Station;

public class ShortestRouteStrategy extends AbstractMetroPathStrategy {

    @Override
    public void doAction(MetroGraph graph, Station station1, Station station2) {
        graph.outputShortestPath(station1, station2);
    }

}
