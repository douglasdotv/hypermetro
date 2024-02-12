package br.com.dv.metro.exception;

public class RouteNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Route between station %s and station %s not found";

    public RouteNotFoundException(String sourceStationName, String targetStationName) {
        super(String.format(MESSAGE, sourceStationName, targetStationName));
    }

}
