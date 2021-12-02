package com.rt.client;

import com.rt.model.Sensor;
import com.rt.model.SensorData;
import com.rt.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerListener {
    @Autowired
    private SensorService sensorService;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeJson(TopicSensorData topicSensorData) {
        log.info("Message from kafka: " + topicSensorData.toString());
        final SensorData sensorData = new SensorData(topicSensorData.getTemperature(),
                topicSensorData.getHumidity(), topicSensorData.getTimestamp());
        final Sensor sensor = sensorService.getSensor(topicSensorData.getSensorId());
        sensorData.setSensor(sensor);
        sensorService.saveSensorData(sensorData);
        log.info("SensorData saved: " + topicSensorData.toString());
    }
}