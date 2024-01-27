package br.com.dv.metro;

public class PrependStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroLine line, String stationName) {
        line.prepend(stationName);
    }

}
