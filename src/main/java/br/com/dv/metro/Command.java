package br.com.dv.metro;

public enum Command {

    APPEND("append"),
    PREPEND("add-head"),
    REMOVE("remove"),
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
