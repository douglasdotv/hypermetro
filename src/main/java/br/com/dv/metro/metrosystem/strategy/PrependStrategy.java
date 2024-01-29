package br.com.dv.metro.metrosystem.strategy;

import br.com.dv.metro.metrosystem.MetroLine;
import br.com.dv.metro.metrosystem.model.Station;

import java.util.ArrayList;

public class PrependStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroLine line, String stationName) {
        Station station = new Station(stationName, line, new ArrayList<>());
        line.prepend(station);
    }

}
