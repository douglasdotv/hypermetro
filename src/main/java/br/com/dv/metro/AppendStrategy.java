package br.com.dv.metro;

public class AppendStrategy extends AbstractMetroLineUpdateStrategy {

    @Override
    protected void doAction(MetroLine line, String stationName) {
        line.append(stationName);
    }

}
