package com.rt.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sensordata")
public class SensorData implements Serializable {

  private static final long serialVersionUID=1L;

  public SensorData(){}

  public SensorData(final double temperature, final double humidity, final Timestamp timestamp) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.timestamp = timestamp;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "data_id")
  private BigDecimal id;

  private double temperature;

  private double humidity ;

  private Timestamp timestamp;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sensor_id")
  private Sensor sensor;

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public Sensor getSensor() {
    return sensor;
  }

  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public double getHumidity() {
    return humidity;
  }

  public void setHumidity(double humidity) {
    this.humidity = humidity;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SensorData sensor = (SensorData) o;
    return Objects.equals(this.id, sensor.id) &&
        Objects.equals(this.temperature, sensor.temperature) &&
        Objects.equals(this.humidity, sensor.humidity) &&
        Objects.equals(this.timestamp, sensor.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, temperature, humidity, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    temperature: ").append(toIndentedString(temperature)).append("\n");
    sb.append("    humidity: ").append(toIndentedString(humidity)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(final Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
