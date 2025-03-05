package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 */
public class Consumer {

    private static final Random RANDOM = new Random();
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final String SENSOR_REGISTRATION_URL = "http://localhost:8080/sensors/registration";
    private static final String MEASUREMENTS_URL = "http://localhost:8080/measurements/add";

    public static void main(String[] args) {
        Sensor sensor = registerSensor(""); // Введите уникальное имя
        sendRequests(sensor);
    }

    public static Sensor registerSensor(String name) {
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(Collections.singletonMap("name", name));
        REST_TEMPLATE.postForObject(SENSOR_REGISTRATION_URL, entity, String.class);

        return new Sensor(name);
    }

    public static void sendRequests(Sensor sensor) {
        for (int i = 0; i < 1000; i++) {
            HttpEntity<Map<String, Object>> entity = createMeasurementEntity(sensor);
            REST_TEMPLATE.postForObject(MEASUREMENTS_URL, entity, String.class);
        }
    }

    private static HttpEntity<Map<String, Object>> createMeasurementEntity(Sensor sensor) {
        HashMap<String, Object> jsonToSend = new HashMap<>();

        jsonToSend.put("value", RANDOM.nextFloat() * 200 - 100);
        jsonToSend.put("raining", RANDOM.nextBoolean());
        jsonToSend.put("sensor", sensor);

        return new HttpEntity<>(jsonToSend);
    }
}
