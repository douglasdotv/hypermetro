package br.com.dv.metro.util;

import br.com.dv.metro.exception.RouteNotFoundException;
import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.model.Edge;
import br.com.dv.metro.metrosystem.model.Station;

import java.util.*;
import java.util.function.ToIntFunction;

public final class PathFinder {

    private PathFinder() {
    }

    public static List<Station> findShortestPath(MetroGraph graph, Station source, Station target) {
        return findPath(graph, source, target, edge -> edge.isTransfer() ? 0 : 1).path();
    }

    public static PathResult findFastestPath(MetroGraph graph, Station source, Station target) {
        return findPath(graph, source, target, Edge::weight);
    }

    private static PathResult findPath(MetroGraph graph, Station source, Station target,
                                       ToIntFunction<Edge> weightFunction) {
        Map<Station, Integer> times = new HashMap<>();
        Queue<Station> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(times::get));
        Map<Station, Station> predecessors = new HashMap<>();

        graph.getAllStations().forEach(station -> times.put(station, Integer.MAX_VALUE));
        times.put(source, 0);
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            Station current = priorityQueue.poll();

            if (current.equals(target)) {
                List<Station> path = reconstructPath(predecessors, target);
                int totalTime = times.get(target);
                return new PathResult(path, totalTime);
            }

            graph.getNeighbors(current).forEach(edge -> {
                Station neighbor = edge.target();
                int weight = weightFunction.applyAsInt(edge);
                int newTime = times.get(current) + weight;
                if (newTime < times.get(neighbor)) {
                    times.put(neighbor, newTime);
                    priorityQueue.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            });
        }

        throw new RouteNotFoundException(source.name(), target.name());
    }

    private static List<Station> reconstructPath(Map<Station, Station> predecessors, Station target) {
        LinkedList<Station> path = new LinkedList<>();
        for (Station current = target; current != null; current = predecessors.get(current)) {
            path.addFirst(current);
        }
        return path;
    }

    public record PathResult(List<Station> path, int totalTime) {
    }

}
