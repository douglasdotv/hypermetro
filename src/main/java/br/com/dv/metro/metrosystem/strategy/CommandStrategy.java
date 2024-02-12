package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroGraph;
import br.com.dv.metro.metrosystem.MetroLine;

import java.util.List;
import java.util.Map;

public interface CommandStrategy {

    void execute(List<String> input, Map<String, MetroLine> metroLines, MetroGraph graph);

}
