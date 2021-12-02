package com.rt.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rt.model.Sensor;
import com.rt.model.SensorData;
import com.rt.rest.controller.SensorRestController;
import com.rt.service.SensorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SensorRestControllerTest {
        @Mock
        private SensorService sensorService;

        private Sensor sensor;
        private SensorData sensorData;
        @InjectMocks
        private SensorRestController sensorRestController;

        @Autowired
        private MockMvc mockMvc;

        @BeforeEach
        public void setup(){
            mockMvc = MockMvcBuilders.standaloneSetup(sensorRestController).build();
            sensor = new Sensor(new BigDecimal(100), "myCity", "myCountry");
            sensorData = new SensorData( 1.1, 1.1, new Timestamp(System.currentTimeMillis()));
        }

        @Test
        public void PostMappingOfSensor() throws Exception{
                when(sensorService.saveSensor(any())).thenReturn(sensor);
                mockMvc.perform(MockMvcRequestBuilders.post("/v1/sensors/sensor/100/register").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(sensor))).
                        andExpect(status().isCreated());

                verify(sensorService,times(1)).saveSensor(sensor);
        }

        @Test
        public void GetMappingOfSensorDataByDates() throws Exception {
                when(sensorService.getSensorQueryResultByDates(any(), any(), any())).thenReturn("mySensorData");
                mockMvc.perform(MockMvcRequestBuilders.get("/v1/sensors/query").
                        contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"ids\": [\"100\", \"200\"],\n" +
                        "    \"parameters\": [\"temperature\", \"humidity\"],\n" +
                        "    \"dates\":[\"2020-01-01\", \"2020-01-02\" ]\n" +
                        "}")).
                        andDo(MockMvcResultHandlers.print()).
                        andExpect(status().isOk());
                verify(sensorService,times(1)).getSensorQueryResultByDates(any(), any(), any());
        }

        @Test
        public void GetMappingOfSensorDataByDateRange() throws Exception {
                when(sensorService.getSensorQueryResultByDateRange(any(), any(), any(), any())).thenReturn("mySensorData");
                mockMvc.perform(MockMvcRequestBuilders.get("/v1/sensors/query").
                        contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"ids\": [\"100\", \"200\"],\n" +
                        "    \"parameters\": [\"temperature\", \"humidity\"],\n" +
                        "    \"dateRange\":{ \"from\":\"2020-01-01\", \"to\":\"2020-01-02\"}\n" +
                        "}")).
                        andDo(MockMvcResultHandlers.print()).
                        andExpect(status().isOk());
                verify(sensorService,times(1)).getSensorQueryResultByDateRange(any(), any(), any(), any());
        }
        private static String asJsonString(final Object obj){
                try{
                        return new ObjectMapper().writeValueAsString(obj);
                }catch (Exception e){
                        throw new RuntimeException(e);
                }
        }

        @AfterEach
        void tearDown() {
            sensor = null;
        }
}
