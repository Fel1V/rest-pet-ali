package com.example.RestPetAli.dto;

import jakarta.validation.constraints.Size;

public class SensorDTO {

    public SensorDTO() {
    }

    @Size(min = 3, max = 30, message = "Имя должно быть в пределах от 3 до 30 символов")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
