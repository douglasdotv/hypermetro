package br.com.dv.metro.metrosystem.model;

import br.com.dv.metro.metrosystem.MetroLine;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public record Station(String name, MetroLine line, @SerializedName("transfer") List<Transfer> transfers, int time) {
}
