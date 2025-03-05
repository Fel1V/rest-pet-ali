package com.example.RestPetAli.controllers;

import com.example.RestPetAli.dto.MeasurementDTO;
import com.example.RestPetAli.models.Measurement;
import com.example.RestPetAli.models.Sensor;
import com.example.RestPetAli.services.MeasurementsService;
import com.example.RestPetAli.services.SensorsService;
import com.example.RestPetAli.util.MeasurementDTOValidator;
import com.example.RestPetAli.util.SensorNotRegistratedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;

    private final SensorsService sensorsService;

    private final MeasurementDTOValidator measurementDTOValidator;

    private final ModelMapper modelMapper;

    public MeasurementsController(MeasurementsService measurementsService, SensorsService sensorsService, MeasurementDTOValidator measurementDTOValidator, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.sensorsService = sensorsService;
        this.measurementDTOValidator = measurementDTOValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MeasurementDTO> getAll() {
        return measurementsService.findAll()
                .stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("rainyDaysCount")
    public int rainyDaysCount() {
        return measurementsService.rainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        measurementDTOValidator.validate(measurementDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError fieldError: errors) {
                errorMsg.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotRegistratedException(errorMsg.toString());
        }

        measurementsService.save(convertToMeasurement(measurementDTO));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {

        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {

        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
