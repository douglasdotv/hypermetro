package br.com.dv.metro;

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
            Command.OUTPUT.getInput(), new OutputStrategy()
    );

    public MetroSystem(Map<String, Map<String, String>> metroLines) {
        metroLines.forEach(this::addLine);
    }

    public void run() {
        while (true) {
            List<String> input = InputHandler.parseInput();
            String command = input.get(0);

            if (command.equals(Command.EXIT.getInput())) {
                return;
            }

            CommandStrategy strategy = this.commandStrategies.getOrDefault(command, new InvalidStrategy());
            strategy.execute(input, this.metroLines);
        }
    }

    private void addLine(String name, Map<String, String> stations) {
        MetroLine metroLine = new MetroLine();

        stations.keySet().stream()
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .forEachOrdered(key -> {
                    String stationName = stations.get(key);
                    metroLine.append(stationName);
                });

        this.metroLines.put(name.toLowerCase(), metroLine);
    }

}
