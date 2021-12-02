package com.rt.service;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.model.QueryParam;
import com.rt.model.Sensor;
import com.rt.model.SensorData;
import com.rt.model.custom.CustomSensorData;
import com.rt.repository.SensorDataJpaRepository;
import com.rt.repository.SensorJpaRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SensorServiceTest {

    @Mock
    private SensorDataJpaRepository sensorDataJpaRepository;

    @Mock
    private SensorJpaRepository sensorJpaRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    @InjectMocks
    private SensorService sensorService = new SensorService(sensorJpaRepository, sensorDataJpaRepository, objectMapper);


    @Test
    void givenSensorToSaveShouldReturnSensor(){
        //when
        final Sensor sensor = new Sensor(new BigDecimal(1), "myCity", "myCountry");
        when(sensorJpaRepository.save(any())).thenReturn(sensor);

        //then
        sensorService.saveSensor(sensor);

        //verify
        verify(sensorJpaRepository,times(1)).save(sensor);
    }

    @Test
    void givenSensorDataToSaveShouldReturnSensorData(){
        //when
        final SensorData sensorData = new SensorData(1.1, 1.1, new Timestamp(System.currentTimeMillis()));
        when(sensorDataJpaRepository.save(any())).thenReturn(sensorData);

        //then
        sensorService.saveSensorData(sensorData);

        //verify
        verify(sensorDataJpaRepository,times(1)).save(sensorData);
    }

    @Test
    public void givenDateRangeThenShouldReturnSensorOfThatId() throws JsonProcessingException {
        //when
        final SensorData sensorData = new SensorData(1.1, 1.1, new Timestamp(System.currentTimeMillis()));
        when(sensorDataJpaRepository.findByIdsAndDateRange(any(), any(), any())).thenReturn(List.of(
                new CustomSensorData(new BigDecimal(1), 1.1, 1.1, 2)));

        //then
        sensorService.getSensorQueryResultByDateRange(List.of(new BigDecimal(1)),
                Date.valueOf("2020-01-01"), Date.valueOf("2020-01-10"), Set.of(QueryParam.temperature));

        //verify
        verify(sensorDataJpaRepository,times(1)).findByIdsAndDateRange(any(), any(), any());
    }

    @Test
    public void givenDatesThenShouldReturnSensorOfThatId() throws JsonProcessingException {
        //when
        final SensorData sensorData = new SensorData(1.1, 1.1, new Timestamp(System.currentTimeMillis()));
        when(sensorDataJpaRepository.findByIdsAndDates(any(), any())).thenReturn(List.of(
                new CustomSensorData(new BigDecimal(1), 1.1, 1.1, 2)));


        //then
        sensorService.getSensorQueryResultByDates(List.of(new BigDecimal(1)),
                List.of(Date.valueOf("2020-01-01"), Date.valueOf("2020-01-10")), Set.of(QueryParam.temperature));

        //verify
        verify(sensorDataJpaRepository,times(1)).findByIdsAndDates(any(), any());
    }
}
