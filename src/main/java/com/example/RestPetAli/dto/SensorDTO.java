package com.example.RestPetAli.dto;

public class SensorDTO {

    public SensorDTO() {
    }

    public SensorDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
