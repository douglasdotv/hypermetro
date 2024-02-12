package br.com.dv.metro.metrosystem;

import br.com.dv.metro.enums.Command;
import br.com.dv.metro.exception.MetroLineNotFoundException;
import br.com.dv.metro.exception.StationNotFoundException;
import br.com.dv.metro.metrosystem.model.Station;
import br.com.dv.metro.metrosystem.model.StationDTO;
import br.com.dv.metro.metrosystem.strategy.*;
import br.com.dv.metro.util.InputHandler;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetroSystem {
    
    private final MetroGraph metroGraph = new MetroGraph();
    private final Map<String, MetroLine> metroLines = new HashMap<>();
    private final Map<String, CommandStrategy> commandStrategies = Map.of(
            Command.APPEND.getInput(), new AppendStrategy(),
            Command.PREPEND.getInput(), new PrependStrategy(),
            Command.REMOVE.getInput(), new RemoveStrategy(),
            Command.CONNECT.getInput(), new ConnectStrategy(),
            Command.SHORTEST_ROUTE.getInput(), new ShortestRouteStrategy(),
            Command.OUTPUT.getInput(), new OutputStrategy()
    );

    public MetroSystem(Map<String, Map<String, StationDTO>> metroLines) {
        this.init(metroLines);
    }

    public void run() {
        while (true) {
            try {
                List<String> input = InputHandler.parseInput();
                String command = input.get(0);

                if (command.equals(Command.EXIT.getInput())) {
                    return;
                }

                CommandStrategy strategy = this.commandStrategies.getOrDefault(command, new InvalidStrategy());
                strategy.execute(input, this.metroLines, this.metroGraph);
            } catch (MetroLineNotFoundException | StationNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void init(Map<String, Map<String, StationDTO>> metroLines) {
        this.buildMetroLines(metroLines);
        this.buildMetroGraph();
    }

    private void buildMetroLines(Map<String, Map<String, StationDTO>> metroLines) {
        metroLines.forEach((lineName, stations) -> {
            MetroLine metroLine = new MetroLine(lineName.toLowerCase());
            this.metroLines.put(lineName.toLowerCase(), metroLine);

            stations.keySet().stream()
                    .sorted(Comparator.comparingInt(Integer::parseInt))
                    .forEachOrdered(key -> {
                        StationDTO dto = stations.get(key);
                        Station station = new Station(dto.name(), metroLine, dto.transfers());
                        metroLine.append(station);
                    });
        });
    }

    private void buildMetroGraph() {
        this.metroLines.values().forEach(line -> {
            List<Station> lineStations = line.getStations();
            Station previousStation = null;

            for (Station station : lineStations) {
                this.metroGraph.addStation(station);

                if (previousStation != null) {
                    this.metroGraph.addEdge(previousStation, station, false);
                }

                previousStation = station;

                if (station.transfers().isEmpty()) {
                    continue;
                }

                this.addStationTransfersAsGraphEdges(station);
            }
        });
    }

    private void addStationTransfersAsGraphEdges(Station sourceStation) {
        sourceStation.transfers().forEach(transfer -> {
            String targetLineName = transfer.line().toLowerCase();
            MetroLine targetLine = this.metroLines.get(targetLineName);

            if (targetLine == null) {
                throw new MetroLineNotFoundException(targetLineName);
            }

            Station targetStation = targetLine.getStation(transfer.station());
            metroGraph.addEdge(sourceStation, targetStation, true);
        });
    }

}
