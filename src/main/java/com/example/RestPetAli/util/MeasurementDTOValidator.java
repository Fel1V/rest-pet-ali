package com.example.RestPetAli.util;

import com.example.RestPetAli.dto.MeasurementDTO;
import com.example.RestPetAli.services.MeasurementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementDTOValidator implements Validator {

    private final MeasurementsService measurementService;

    @Autowired
    public MeasurementDTOValidator(MeasurementsService measurementService) {
        this.measurementService = measurementService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO
    }
}
