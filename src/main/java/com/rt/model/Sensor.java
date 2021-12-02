package com.rt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {

  private static final long serialVersionUID=1L;

  public Sensor(){}

  public Sensor(final BigDecimal sensorId, final String city, final String country) {
    this.id = sensorId;
    this.city = city;
    this.country = country;
  }

  @Id
  @Column(name = "sensor_id")
  private BigDecimal id;

  private String city;

  private String country ;

  public BigDecimal getId() {
    return id;
  }

  public void setId(final BigDecimal sensorId) {
    this.id = sensorId;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sensor sensor = (Sensor) o;
    return Objects.equals(this.id, sensor.id) &&
        Objects.equals(this.city, sensor.city) &&
        Objects.equals(this.country, sensor.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, city, country);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
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
