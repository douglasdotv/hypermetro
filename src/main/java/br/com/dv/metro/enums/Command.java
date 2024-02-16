package br.com.dv.metro.enums;

public enum Command {

    APPEND("append"),
    PREPEND("add-head"),
    REMOVE("remove"),
    CONNECT("connect"),
    FASTEST_ROUTE("fastest-route"),
    SHORTEST_ROUTE("route"),
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
