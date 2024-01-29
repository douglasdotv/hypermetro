package br.com.dv.metro.metrosystem.model;

import java.util.List;

public class StationNode {

    private final Station station;
    private StationNode previous;
    private StationNode next;

    public StationNode(Station station) {
        this.station = station;
    }

    public void addTransfer(Transfer transfer) {
        this.station.transfers().add(transfer);
    }

    public String getName() {
        return station.name();
    }

    public List<Transfer> getTransfers() {
        return station.transfers();
    }

    public StationNode getPrevious() {
        return previous;
    }

    public void setPrevious(StationNode previous) {
        this.previous = previous;
    }

    public StationNode getNext() {
        return next;
    }

    public void setNext(StationNode next) {
        this.next = next;
    }

}
