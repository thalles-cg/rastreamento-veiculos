package com.rastreamento.model;

import java.time.LocalDateTime;

public class Localizacao {
  private String veiculoId;
  private double latitude;
  private double longitude;
  private LocalDateTime dataHora;

  public Localizacao() {}

  public Localizacao(
    String veiculoId,
    double latitude,
    double longitude,
    LocalDateTime dataHora) {

    this.veiculoId = veiculoId;
    this.latitude = latitude;
    this.longitude = longitude;
    this.dataHora = dataHora;
  }

  public String getVeiculoId() {
    return veiculoId;
  }

  public void setVeiculoId(String veiculoId) {
    this.veiculoId = veiculoId;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
  }

  @Override
  public String toString() {
    return "Veículo: " + veiculoId
      + " | Latitude: " + latitude
      + " | Longitude: " + longitude
      + " | Data/Hora: " + dataHora;
  }
}