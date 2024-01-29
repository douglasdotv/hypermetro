package br.com.dv.metro.metrosystem.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public record StationDTO(String name, @SerializedName("transfer") List<Transfer> transfers) {
}
