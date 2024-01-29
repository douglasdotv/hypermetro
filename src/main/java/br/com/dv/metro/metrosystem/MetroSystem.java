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

    private final Map<String, MetroLine> metroLines = new HashMap<>();
    private final Map<String, CommandStrategy> commandStrategies = Map.of(
            Command.APPEND.getInput(), new AppendStrategy(),
            Command.PREPEND.getInput(), new PrependStrategy(),
            Command.REMOVE.getInput(), new RemoveStrategy(),
            Command.CONNECT.getInput(), new ConnectStrategy(),
            Command.OUTPUT.getInput(), new OutputStrategy()
    );

    public MetroSystem(Map<String, Map<String, StationDTO>> metroLines) {
        metroLines.forEach(this::addLine);
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
                strategy.execute(input, this.metroLines);
            } catch (MetroLineNotFoundException | StationNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void addLine(String lineName, Map<String, StationDTO> stations) {
        MetroLine metroLine = new MetroLine(lineName.toLowerCase());

        stations.keySet().stream()
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .forEachOrdered(key -> {
                    StationDTO dto = stations.get(key);
                    Station station = new Station(dto.name(), metroLine, dto.transfers());
                    metroLine.append(station);
                });

        this.metroLines.put(lineName.toLowerCase(), metroLine);
    }

}
