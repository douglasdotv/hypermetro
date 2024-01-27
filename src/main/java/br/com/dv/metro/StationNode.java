package br.com.dv.metro;

public class StationNode {

    private final String name;
    private StationNode previous;
    private StationNode next;

    public StationNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
