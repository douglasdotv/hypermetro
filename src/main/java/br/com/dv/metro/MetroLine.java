package br.com.dv.metro;

public class MetroLine {

    private static final String LINE_EXTREMITY = "Depot";

    private final StationNode head;
    private final StationNode tail;
    private int totalStations;

    public MetroLine() {
        this.head = new StationNode(LINE_EXTREMITY);
        this.tail = new StationNode(LINE_EXTREMITY);
        this.head.setNext(this.tail);
        this.totalStations = 0;
    }

    public void addStation(String name) {
        StationNode newStation = new StationNode(name);
        newStation.setNext(this.tail);

        StationNode current = this.head;
        while (current.getNext() != this.tail) {
            current = current.getNext();
        }
        current.setNext(newStation);

        this.totalStations++;
    }

    public void outputStations() {
        if (this.totalStations == 0) {
            return;
        }

        StringBuilder output = new StringBuilder();

        StationNode current = this.head;
        while (current.getNext() != this.tail) {
            output.append(current.getName())
                    .append(" - ")
                    .append(current.getNext().getName())
                    .append(" - ")
                    .append(current.getNext().getNext().getName())
                    .append("\n");

            current = current.getNext();
        }

        System.out.println(output);
    }

}
