package com.example.RestPetAli.services;

import com.example.RestPetAli.models.Measurement;
import com.example.RestPetAli.models.Sensor;
import com.example.RestPetAli.repositories.MeasurementsRepository;
import com.example.RestPetAli.repositories.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    private final SensorsRepository sensorsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsRepository sensorsRepository) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsRepository = sensorsRepository;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public int rainyDaysCount() {
        return measurementsRepository.findAllByRaining(true).size();
    }

    @Transactional
    public void save(Measurement measurement) {
//        Sensor sensor = sensorsRepository.findByName(measurement.getSensor().getName()).get();
//        measurement.setSensor(sensor);
        measurementsRepository.save(measurement);
    }
}
