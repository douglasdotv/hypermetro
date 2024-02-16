package br.com.dv.metro.util;

import br.com.dv.metro.metrosystem.model.Station;
import br.com.dv.metro.metrosystem.model.StationNode;
import br.com.dv.metro.metrosystem.model.Transfer;

import java.io.PrintWriter;
import java.util.List;

public final class MetroOutput {

    private static final String INVALID_COMMAND_MESSAGE = "Invalid command";
    private static final String STATION_TRANSFER_FORMAT = " - %s (%s line)";
    private static final String TRANSFER_TO_LINE_FORMAT = "Transition to line %s\n";
    private static final String TOTAL_TIME_FORMAT = "Total: %d minutes in the way\n";

    private static final PrintWriter out = new PrintWriter(System.out, true);
    private static final PrintWriter err = new PrintWriter(System.err, true);

    private MetroOutput() {
    }

    public static void output(String message) {
        out.println(message);
    }

    public static void outputErr(String message) {
        err.println(message);
    }

    public static void outputInvalidCommand() {
        out.println(INVALID_COMMAND_MESSAGE);
    }

    public static void outputStations(StationNode head) {
        StringBuilder output = new StringBuilder();

        for (StationNode current = head; current != null; current = current.getNext()) {
            output.append(current.getName());
            if (!current.getTransfers().isEmpty()) {
                for (Transfer transfer : current.getTransfers()) {
                    output.append(String.format(STATION_TRANSFER_FORMAT, transfer.station(), transfer.line()));
                }
            }
            output.append("\n");
        }

        out.println(output);
    }

    public static void outputShortestPath(List<Station> path) {
        StringBuilder output = new StringBuilder();

        Station previousStation = null;
        for (Station station : path) {
            if (previousStation != null && !station.line().equals(previousStation.line())) {
                output.append(String.format(TRANSFER_TO_LINE_FORMAT, station.line().getName()));
            }
            output.append(station.name()).append("\n");
            previousStation = station;
        }

        out.println(output);
    }

    public static void outputFastestPath(List<Station> path, int totalTime) {
        StringBuilder output = new StringBuilder();

        Station previousStation = null;
        for (Station station : path) {
            boolean isTransfer = previousStation != null && !station.line().equals(previousStation.line());
            if (isTransfer) {
                output.append(String.format(TRANSFER_TO_LINE_FORMAT, station.line().getName()));
            }
            output.append(station.name()).append("\n");
            previousStation = station;
        }
        output.append(String.format(TOTAL_TIME_FORMAT, totalTime));

        out.println(output);
    }

}
