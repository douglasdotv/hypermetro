package br.com.dv.metro.enums;

public enum Command {

    APPEND("append"),
    PREPEND("add-head"),
    REMOVE("remove"),
    CONNECT("connect"),
    OUTPUT("output"),
    EXIT("exit");

    private final String input;

    Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

}
