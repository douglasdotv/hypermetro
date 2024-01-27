package br.com.dv.metro;

public class RemoveStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroLine line, String stationName) {
        line.remove(stationName);
    }

}
