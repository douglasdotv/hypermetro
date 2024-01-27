package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroLine;

public class AppendStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroLine line, String stationName) {
        line.append(stationName);
    }

}
