package com.rt.repository;

import com.rt.model.SensorData;
import com.rt.model.custom.CustomSensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface SensorDataJpaRepository extends JpaRepository<SensorData, BigDecimal> {


    List<SensorData> findBySensor_Id(BigDecimal id);

    @Query("Select new com.rt.model.custom.CustomSensorData(data.sensor.id, AVG(data.temperature), AVG(data.humidity),"
            + " count(data.sensor.id)) "
            + " From SensorData data where (data.sensor.id in (:idList)) and (timestamp between :from and :to)"
            + " GROUP BY data.sensor.id ORDER BY data.sensor.id DESC")
    List<CustomSensorData> findByIdsAndDateRange(List<BigDecimal> idList, Date from, Date to);

    @Query("Select new com.rt.model.custom.CustomSensorData(data.sensor.id, AVG(data.temperature), AVG(data.humidity),"
            + " count(data.sensor.id)) "
            + " From SensorData data where (data.sensor.id in (:idList)) and (TRUNC(timestamp) in :dates)"
            + " GROUP BY data.sensor.id ORDER BY data.sensor.id DESC")
    List<CustomSensorData> findByIdsAndDates(List<BigDecimal> idList, List<Date> dates);

}
