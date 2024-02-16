package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.model.Station;

public class FastestRouteStrategy extends AbstractMetroPathStrategy {

    @Override
    public void doAction(MetroGraph graph, Station station1, Station station2) {
        graph.outputFastestPath(station1, station2);
    }

}
