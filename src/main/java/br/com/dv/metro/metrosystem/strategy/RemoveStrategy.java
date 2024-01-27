package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroLine;

public class RemoveStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroLine line, String stationName) {
        line.remove(stationName);
    }

}
