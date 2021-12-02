package com.rt.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TopicSensorData {
    private BigDecimal sensorId;
    private double temperature;
    private double humidity;
    private Timestamp timestamp;
}