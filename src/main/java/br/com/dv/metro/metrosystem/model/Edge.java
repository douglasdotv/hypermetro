package br.com.dv.metro.metrosystem.model;

public record Edge(Station source, Station target, int weight, boolean isTransfer) {

    private static final int DEFAULT_WEIGHT = 1;

    public Edge(Station source, Station target, boolean isTransfer) {
        this(source, target, DEFAULT_WEIGHT, isTransfer);
    }

}
