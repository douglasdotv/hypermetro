package br.com.dv.metro.metrosystem;

import br.com.dv.metro.exception.StationNotFoundException;
import br.com.dv.metro.metrosystem.model.Station;
import br.com.dv.metro.metrosystem.model.StationNode;
import br.com.dv.metro.metrosystem.model.Transfer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MetroLine {

    private static final String LINE_EXTREMITY = "Depot";

    private final String name;
    private final StationNode head;
    private final StationNode tail;
    private int totalStations;

    public MetroLine(String lineName) {
        this.name = lineName;

        Station depot = new Station(LINE_EXTREMITY, this, Collections.emptyList());
        this.head = new StationNode(depot);
        this.tail = new StationNode(depot);
        this.head.setNext(this.tail);
        this.tail.setPrevious(this.head);

        this.totalStations = 0;
    }

    public void append(Station station) {
        StationNode newStation = new StationNode(station);
        StationNode lastStation = this.tail.getPrevious();

        newStation.setPrevious(lastStation);
        lastStation.setNext(newStation);

        newStation.setNext(this.tail);
        this.tail.setPrevious(newStation);

        this.totalStations++;
    }

    public void prepend(Station station) {
        StationNode newStation = new StationNode(station);
        StationNode firstStation = this.head.getNext();

        newStation.setNext(firstStation);
        firstStation.setPrevious(newStation);

        newStation.setPrevious(this.head);
        this.head.setNext(newStation);

        this.totalStations++;
    }

    public StationNode remove(String stationName) {
        for (StationNode current = this.head.getNext(); current != this.tail; current = current.getNext()) {
            if (current.getName().equalsIgnoreCase(stationName)) {
                StationNode currentPrevious = current.getPrevious();
                StationNode currentNext = current.getNext();

                currentPrevious.setNext(currentNext);
                currentNext.setPrevious(currentPrevious);

                this.totalStations--;
                this.removeTransfersToStation(stationName);
                return current;
            }
        }
        throw new StationNotFoundException(stationName);
    }

    public void connect(String currentStationName, MetroLine otherLine, String otherStationName) {
        StationNode currentStation = this.getStationNode(currentStationName);
        StationNode otherStation = otherLine.getStationNode(otherStationName);

        Transfer transferToOther = new Transfer(otherLine.getName(), otherStationName);
        Transfer transferToCurrent = new Transfer(this.name, currentStationName);

        currentStation.addTransfer(transferToOther);
        otherStation.addTransfer(transferToCurrent);
    }

    public void outputStations() {
        if (this.totalStations == 0) {
            return;
        }

        StringBuilder output = new StringBuilder();

        for (StationNode current = this.head; current != null; current = current.getNext()) {
            output.append(current.getName());

            if (!current.getTransfers().isEmpty()) {
                for (Transfer transfer : current.getTransfers()) {
                    output.append(" - ")
                            .append(transfer.station())
                            .append(" (")
                            .append(transfer.line())
                            .append(" line)");
                }
            }

            output.append("\n");
        }

        System.out.println(output);
    }

    private StationNode getStationNode(String stationName) {
        for (StationNode current = this.head.getNext(); current != this.tail; current = current.getNext()) {
            if (current.getName().equalsIgnoreCase(stationName)) {
                return current;
            }
        }
        throw new StationNotFoundException(stationName);
    }

    private void removeTransfersToStation(String stationName) {
        for (StationNode current = this.head.getNext(); current != this.tail; current = current.getNext()) {
            current.getTransfers().removeIf(transfer -> transfer.station().equalsIgnoreCase(stationName));
        }
    }

    public String getName() {
        return name;
    }

    public Station getStation(String stationName) {
        return this.getStationNode(stationName).getStation();
    }

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<>();
        for (StationNode current = this.head.getNext(); current != this.tail; current = current.getNext()) {
            stations.add(current.getStation());
        }
        return stations;
    }

    public Station getFirstStation() {
        return this.head.getNext().getStation();
    }

    public Station getLastStation() {
        return this.tail.getPrevious().getStation();
    }

}
