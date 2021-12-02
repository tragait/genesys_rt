package com.rt.model.custom;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonFilter("ParamFilter")
public class CustomSensorData {

    public  CustomSensorData(){}

    public CustomSensorData(BigDecimal sensor_id, double temperature, double humidity, long total){
        this.sensor_id = sensor_id;
        this.avgHumidity = humidity;
        this.avgTemperature = temperature;
        this.totalCount = total;
    }

    private BigDecimal sensor_id;
    private double avgTemperature;
    private double avgHumidity;
    private long totalCount;
}
