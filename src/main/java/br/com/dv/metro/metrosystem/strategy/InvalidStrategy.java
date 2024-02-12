package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;

import java.util.List;
import java.util.Map;

public class InvalidStrategy implements CommandStrategy {

    private static final String INVALID_COMMAND_MESSAGE = "Invalid command";

    @Override
    public void execute(List<String> input, Map<String, MetroLine> metroLines, MetroGraph graph) {
        System.out.println(INVALID_COMMAND_MESSAGE);
    }

}
