package com.example.RestPetAli.dto;

import com.example.RestPetAli.models.Sensor;

public class MeasurementDTO {

    public MeasurementDTO() {
    }


    private float value;

    private boolean raining;

    private Sensor sensor;


    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
