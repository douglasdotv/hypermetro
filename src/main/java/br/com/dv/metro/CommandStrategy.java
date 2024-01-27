package br.com.dv.metro;

import java.util.List;
import java.util.Map;

public interface CommandStrategy {

    void execute(List<String> input, Map<String, MetroLine> metroLines);

}
