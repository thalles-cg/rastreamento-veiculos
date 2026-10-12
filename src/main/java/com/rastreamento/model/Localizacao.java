package com.rastreamento.model;

import java.time.LocalDateTime;

public class Localizacao {
  private String veiculoId;
  private double latitude;
  private double longitude;
  private LocalDateTime dataHora;

  public Localizacao(String veiculoId, double latitude, double longitude, LocalDateTime dataHora) {
    this.veiculoId = veiculoId;
    this.latitude = latitude;
    this.longitude = longitude;
    this.dataHora = dataHora;
  }

  public String getVeiculoId() {
    return veiculoId;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  @Override
  public String toString() {
    return "Veículo: " + veiculoId
      + " | Latitude: " + latitude
      + " | Longitude: " + longitude
      + " | Data/Hora: " + dataHora;
  }
}