package com.example.RestPetAli.util;

import com.example.RestPetAli.dto.MeasurementDTO;
import com.example.RestPetAli.models.Measurement;
import com.example.RestPetAli.models.Sensor;
import com.example.RestPetAli.services.MeasurementsService;
import com.example.RestPetAli.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class MeasurementDTOValidator implements Validator {

    private final MeasurementsService measurementService;

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementDTOValidator(MeasurementsService measurementService, SensorsService sensorsService) {
        this.measurementService = measurementService;
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurement = (MeasurementDTO) target;
        Optional<Sensor> sensor = sensorsService.findByName(measurement.getSensor().getName());

        if (sensor.isPresent()) {
            measurement.setSensor(sensor.get());
        } else {
            errors.rejectValue("sensor", "", "Сенсора с таким именем нет");
        }
    }
}
