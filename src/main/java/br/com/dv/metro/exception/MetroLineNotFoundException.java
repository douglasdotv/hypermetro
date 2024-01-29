package br.com.dv.metro.exception;

public class MetroLineNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Line %s not found";

    public MetroLineNotFoundException(String lineName) {
        super(String.format(MESSAGE, lineName));
    }

}
