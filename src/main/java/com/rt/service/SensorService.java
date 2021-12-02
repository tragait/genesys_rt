package com.rt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rt.exception.SensorException;
import com.rt.model.QueryParam;
import com.rt.model.Sensor;
import com.rt.model.SensorData;
import com.rt.model.custom.CustomSensorData;
import com.rt.repository.SensorDataJpaRepository;
import com.rt.repository.SensorJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class SensorService {
    private SensorJpaRepository sensorJpaRepository;

    private SensorDataJpaRepository sensorDataJpaRepository;

    private ObjectMapper objectMapper;

    public SensorService(final SensorJpaRepository sensorJpaRepository, final SensorDataJpaRepository sensorDataJpaRepository,
                         final ObjectMapper objectMapper){
        this.sensorJpaRepository = sensorJpaRepository;
        this.sensorDataJpaRepository = sensorDataJpaRepository;
        this.objectMapper = objectMapper;
    }

    public Sensor saveSensor(final Sensor sensor){
        return sensorJpaRepository.save(sensor);
    }

    public Sensor getSensor(final BigDecimal sensorId){
        return sensorJpaRepository.findById(sensorId).orElseThrow();
    }

    public SensorData saveSensorData(final SensorData sensorData){
        return sensorDataJpaRepository.save(sensorData);
    }

    public String getSensorQueryResultByDateRange(List<BigDecimal> sensorIds, Date from, Date to, Set<QueryParam> params){

         List<CustomSensorData> sensorDataList = sensorDataJpaRepository.findByIdsAndDateRange(sensorIds,
                from, to);
        return prepareJsonForSensorResultData(sensorDataList, params);
    }

    public String getSensorQueryResultByDates(List<BigDecimal> sensorIds, List<Date> dates, Set<QueryParam> params){
        List<CustomSensorData> sensorDataList = sensorDataJpaRepository.findByIdsAndDates(sensorIds,
                dates);
        return prepareJsonForSensorResultData(sensorDataList, params);

    }

    private String prepareJsonForSensorResultData(List<CustomSensorData> customSensorDataList, Set<QueryParam> params){
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
                .serializeAll();;
        FilterProvider filters;
        if(params.size() == 1 && params.contains(QueryParam.temperature)){
            theFilter = SimpleBeanPropertyFilter
                    .serializeAllExcept("avgHumidity");
        }else if(params.size() == 1 && params.contains(QueryParam.humidity)){
            theFilter = SimpleBeanPropertyFilter
                    .serializeAllExcept("avgTemperature");
        }
        filters = new SimpleFilterProvider()
                .addFilter("ParamFilter", theFilter);
        try {
            return objectMapper.writer(filters).writeValueAsString(customSensorDataList);
        } catch (JsonProcessingException e) {
            log.error("Json exception occurred during object mapping for sensor ids");
            throw new SensorException("Json exception occurred during object mapping for sensor ids", null);
        }

    }
}
