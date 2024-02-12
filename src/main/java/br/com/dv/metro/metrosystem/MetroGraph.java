package br.com.dv.metro.metrosystem;

import br.com.dv.metro.metrosystem.model.Edge;
import br.com.dv.metro.metrosystem.model.Station;
import br.com.dv.metro.util.PathFinder;

import java.util.*;

public class MetroGraph {

    private final Map<Station, Set<Edge>> adjacencyList = new HashMap<>();

    public void addStation(Station station) {
        this.adjacencyList.putIfAbsent(station, new HashSet<>());
    }

    public void addEdge(Station source, Station target, boolean isTransfer) {
        this.addStationsIfAbsent(source, target);
        Edge edge = new Edge(source, target, isTransfer);
        Edge reverseEdge = new Edge(target, source, isTransfer);
        this.adjacencyList.get(source).add(edge);
        this.adjacencyList.get(target).add(reverseEdge);
    }

    public void removeStation(Station station) {
        this.removeStationEdges(station);
        this.adjacencyList.remove(station);
    }

    public void outputShortestPath(Station source, Station target) {
        List<Station> path = PathFinder.findShortestPath(this, source, target);

        StringBuilder output = new StringBuilder();

        Station previousStation = null;
        for (Station station : path) {
            boolean isTransfer = previousStation != null && !station.line().equals(previousStation.line());
            if (isTransfer) {
                output.append(String.format("Transition to line %s%n", station.line().getName()));
            }
            output.append(station.name()).append("\n");
            previousStation = station;
        }

        System.out.println(output);
    }

    private void removeStationEdges(Station station) {
        var stationEdges = this.adjacencyList.get(station);
        var copyOfStationEdges = new LinkedList<>(stationEdges);
        for (Edge edge : copyOfStationEdges) {
            this.removeEdge(edge.source(), edge.target());
        }
    }

    private void removeEdge(Station source, Station target) {
        var sourceEdges = this.adjacencyList.get(source);
        var targetEdges = this.adjacencyList.get(target);
        sourceEdges.removeIf(edge -> edge.target().equals(target));
        targetEdges.removeIf(edge -> edge.target().equals(source));
    }

    private void addStationsIfAbsent(Station station1, Station station2) {
        this.addStation(station1);
        this.addStation(station2);
    }

    public Set<Edge> getNeighbors(Station station) {
        return this.adjacencyList.getOrDefault(station, new HashSet<>());
    }

    public Set<Station> getAllStations() {
        return this.adjacencyList.keySet();
    }

}
