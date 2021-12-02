package com.rt.repository;

import com.rt.model.Sensor;
import com.rt.model.SensorData;
import com.rt.model.custom.CustomSensorData;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class JpaRepositoryTest {

    @Autowired
    private SensorDataJpaRepository sensorDataJpaRepository;

    @Autowired
    private SensorJpaRepository sensorJpaRepository;

    @Test
    public void givenSensorToAddShouldReturnAddedSensor(){
        final Sensor sensor = new Sensor(new BigDecimal(1), "mycity", "mycountry");
        sensorJpaRepository.save(sensor);
        final Sensor fetchedSensor = sensorJpaRepository.findById(sensor.getId()).get();
        Assertions.assertNotNull(fetchedSensor);
    }

    @Test
    public void givenSensorDataToAddShouldReturnAddedSensorData(){
        final Sensor sensor = new Sensor(new BigDecimal(1), "mycity", "mycountry");
        sensorJpaRepository.save(sensor);
        final SensorData sensorData = new SensorData( 1.1, 1.1, new Timestamp(System.currentTimeMillis()));
        sensorData.setSensor(sensor);
        sensorDataJpaRepository.save(sensorData);
        final List<SensorData> fetchedSensorDataList = sensorDataJpaRepository.findBySensor_Id(sensorData.getSensor().getId());
        Assertions.assertNotNull(fetchedSensorDataList);
        Assertions.assertEquals(1, fetchedSensorDataList.size());
    }

    @Test
    public void testSensorDataFindByIdsAndDateRange(){
        setupSensorData();
        final List<CustomSensorData> fetchedSensorDataList = sensorDataJpaRepository.findByIdsAndDateRange(List.of(new BigDecimal(1), new BigDecimal(2)),
                Date.valueOf("2021-01-02"), Date.valueOf("2021-01-04"));
        Assertions.assertNotNull(fetchedSensorDataList);
        Assertions.assertEquals(2, fetchedSensorDataList.size());
    }

    @Test
    public void testSensorDataFindByIdsAndDates(){
        setupSensorData();
        final List<CustomSensorData> fetchedSensorDataList = sensorDataJpaRepository.findByIdsAndDates(List.of(new BigDecimal(1), new BigDecimal(2)),
                List.of(Date.valueOf("2021-01-03"), Date.valueOf("2021-01-04")));
        Assertions.assertNotNull(fetchedSensorDataList);
        Assertions.assertEquals(2, fetchedSensorDataList.size());
    }

    private void setupSensorData() {
        Sensor sensor1 = new Sensor(new BigDecimal(1), "mycity", "mycountry");
        sensorJpaRepository.save(sensor1);

        Sensor sensor2 = new Sensor(new BigDecimal(2), "mycity", "mycountry");
        sensorJpaRepository.save(sensor2);

        SensorData sensorData = new SensorData( 1.1, 1.1, Timestamp.valueOf("2021-01-01 00:00:00.000"));
        sensorData.setSensor(sensor1);
        sensorDataJpaRepository.save(sensorData);

        sensorData = new SensorData( 1.2, 1.2, Timestamp.valueOf("2021-01-02 10:00:00.000"));
        sensorData.setSensor(sensor1);
        sensorDataJpaRepository.save(sensorData);

        sensorData = new SensorData( 1.3, 1.3, Timestamp.valueOf("2021-01-03 00:00:00.000"));
        sensorData.setSensor(sensor1);
        sensorDataJpaRepository.save(sensorData);

        sensorData = new SensorData( 1.4, 1.4, Timestamp.valueOf("2021-01-04 00:00:00.000"));
        sensorData.setSensor(sensor1);
        sensorDataJpaRepository.save(sensorData);

        sensorData = new SensorData( 1.5, 1.5, Timestamp.valueOf("2021-01-05 00:00:00.000"));
        sensorData.setSensor(sensor1);
        sensorDataJpaRepository.save(sensorData);

        sensorData = new SensorData( 2.3, 2.3, Timestamp.valueOf("2021-01-03 00:00:00.000"));
        sensorData.setSensor(sensor2);
        sensorDataJpaRepository.save(sensorData);
    }

    @AfterEach
    public void teardown(){
        sensorJpaRepository.deleteAll();
        sensorDataJpaRepository.deleteAll();
    }
}
