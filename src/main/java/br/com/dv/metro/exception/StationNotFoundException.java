package br.com.dv.metro.exception;

public class StationNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Station %s not found";

    public StationNotFoundException(String stationName) {
        super(String.format(MESSAGE, stationName));
    }

}
