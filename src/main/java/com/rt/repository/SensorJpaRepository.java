package com.rt.repository;

import com.rt.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface SensorJpaRepository extends JpaRepository<Sensor, BigDecimal> {
}
