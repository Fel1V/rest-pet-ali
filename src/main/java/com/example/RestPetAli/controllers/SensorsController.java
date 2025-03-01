package com.example.RestPetAli.controllers;

import com.example.RestPetAli.dto.SensorDTO;
import com.example.RestPetAli.models.Sensor;
import com.example.RestPetAli.services.SensorsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;

    private final ModelMapper modelMapper;

    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<SensorDTO> getSensors() {
        return sensorsService.findAll().stream().map(this::convertToSensorDTO)
                .collect(Collectors.toList());
    }

//    @PostMapping("/registration")
//    public void register() {
//
//    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
