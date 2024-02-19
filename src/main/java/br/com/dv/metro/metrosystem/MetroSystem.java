package br.com.dv.metro.metrosystem;

import br.com.dv.metro.enums.Command;
import br.com.dv.metro.exception.MetroLineNotFoundException;
import br.com.dv.metro.exception.StationNotFoundException;
import br.com.dv.metro.metrosystem.model.Station;
import br.com.dv.metro.metrosystem.model.StationDTO;
import br.com.dv.metro.metrosystem.model.Transfer;
import br.com.dv.metro.metrosystem.strategy.*;
import br.com.dv.metro.util.InputHandler;
import br.com.dv.metro.util.MetroOutput;

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
            Command.FASTEST_ROUTE.getInput(), new FastestRouteStrategy(),
            Command.SHORTEST_ROUTE.getInput(), new ShortestRouteStrategy(),
            Command.OUTPUT.getInput(), new OutputStrategy()
    );

    public MetroSystem(Map<String, List<StationDTO>> metroLinesJsonData) {
        this.init(metroLinesJsonData);
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
                MetroOutput.outputErr(e.getMessage());
            }
        }
    }

    private void init(Map<String, List<StationDTO>> metroLinesJsonData) {
        this.buildMetroLines(metroLinesJsonData);
        this.buildMetroGraph(metroLinesJsonData);
    }

    private void buildMetroLines(Map<String, List<StationDTO>> metroLinesJsonData) {
        metroLinesJsonData.forEach((lineName, stations) -> {
            MetroLine metroLine = new MetroLine(lineName.toLowerCase());
            this.metroLines.put(lineName.toLowerCase(), metroLine);

            stations.forEach(stationDto -> {
                Station station = this.mapDtoToStation(stationDto, metroLine);
                metroLine.append(station);
            });
        });
    }

    private void buildMetroGraph(Map<String, List<StationDTO>> metroLinesJsonData) {
        metroLinesJsonData.forEach((lineName, stationDtos) -> {
            MetroLine metroLine = this.metroLines.get(lineName.toLowerCase());

            if (metroLine == null) {
                throw new MetroLineNotFoundException(lineName);
            }

            stationDtos.forEach(stationDto -> {
                Station station = metroLine.getStation(stationDto.name());

                stationDto.next().forEach(nextStationName -> {
                    Station nextStation = metroLine.getStation(nextStationName);
                    this.metroGraph.addEdge(station, nextStation, false);
                });

                this.addStationTransfersAsGraphEdges(station);
            });
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

    private Station mapDtoToStation(StationDTO dto, MetroLine metroLine) {
        String name = dto.name();
        List<Transfer> transfers = dto.transfers();
        String timeAsString = dto.time();
        int time = timeAsString == null ? 0 : Integer.parseInt(timeAsString);
        return new Station(name, metroLine, transfers, time);
    }

}
