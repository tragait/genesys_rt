package com.rt.rest.controller;

import com.rt.model.Data;
import com.rt.model.Metadata;
import com.rt.model.Query;
import com.rt.model.QueryParam;
import com.rt.model.Sensor;
import com.rt.model.SensorData;
import com.rt.rest.api.DataApi;
import com.rt.rest.api.DataInternalApi;
import com.rt.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.stream.Collectors;


@RestController
public class SensorRestController implements DataApi, DataInternalApi {

    @Autowired
    private SensorService sensorService;

    @Override
    public ResponseEntity<String> registerSensor(final BigDecimal sensorId, @Valid final Metadata body) {
        final Sensor sensor = new Sensor(sensorId, body.getCity(), body.getCountry());
        sensorService.saveSensor(sensor);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> saveSensorData(@Valid Data body) {
        final SensorData sensorData = new SensorData(body.getTemperature().doubleValue(),
                body.getHumidity().doubleValue(), Timestamp.valueOf(body.getTimestamp()));
        final Sensor sensor = sensorService.getSensor(body.getSensorId());
        sensorData.setSensor(sensor);
        sensorService.saveSensorData(sensorData);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> getSensorData(@Valid Query body) {
        final String json;
        if(body.getDates()!=null) {
            json = sensorService.getSensorQueryResultByDates(body.getIds(),
                    body.getDates().stream().map(d -> new java.sql.Date(d.getTime())).collect(Collectors.toList()),
                    body.getParameters().stream().map(p -> QueryParam.valueOf(p.toString())).collect(Collectors.toSet()));
        }else{
            json = sensorService.getSensorQueryResultByDateRange(body.getIds(),
                    new java.sql.Date(body.getDateRange().getFrom().getTime()),
                    new java.sql.Date(body.getDateRange().getTo().getTime()),
                    body.getParameters().stream().map(p -> QueryParam.valueOf(p.toString())).collect(Collectors.toSet()));

        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
