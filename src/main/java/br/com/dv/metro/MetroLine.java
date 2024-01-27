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
        this.tail.setPrevious(this.head);
        this.totalStations = 0;
    }

    public void append(String stationName) {
        StationNode newStation = new StationNode(stationName);
        StationNode lastStation = this.tail.getPrevious();

        newStation.setPrevious(lastStation);
        lastStation.setNext(newStation);

        newStation.setNext(this.tail);
        this.tail.setPrevious(newStation);

        this.totalStations++;
    }

    public void prepend(String stationName) {
        StationNode newStation = new StationNode(stationName);
        StationNode firstStation = this.head.getNext();

        newStation.setNext(firstStation);
        firstStation.setPrevious(newStation);

        newStation.setPrevious(this.head);
        this.head.setNext(newStation);

        this.totalStations++;
    }

    public void remove(String stationName) {
        for (StationNode current = this.head.getNext(); current != this.tail; current = current.getNext()) {
            if (current.getName().equalsIgnoreCase(stationName)) {
                StationNode currentPrevious = current.getPrevious();
                StationNode currentNext = current.getNext();

                currentPrevious.setNext(currentNext);
                currentNext.setPrevious(currentPrevious);

                this.totalStations--;
                break;
            }
        }
    }

    public void outputStations() {
        if (this.totalStations == 0) {
            return;
        }

        StringBuilder output = new StringBuilder();

        for (StationNode current = this.head; current.getNext() != this.tail; current = current.getNext()) {
            output.append(current.getName())
                    .append(" - ")
                    .append(current.getNext().getName())
                    .append(" - ")
                    .append(current.getNext().getNext().getName())
                    .append("\n");
        }

        System.out.println(output);
    }

}
