package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;
import br.com.dv.metro.util.MetroOutput;

import java.util.List;
import java.util.Map;

public class InvalidStrategy implements CommandStrategy {

    @Override
    public void execute(List<String> input, Map<String, MetroLine> metroLines, MetroGraph graph) {
        MetroOutput.outputInvalidCommand();
    }

}
