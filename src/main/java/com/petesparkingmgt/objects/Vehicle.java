package com.petesparkingmgt.objects;

public class Vehicle {


    private String make, model, license, color;

    public Vehicle(String make, String model, String license, String color) {
        this.make = make;
        this.model = model;
        this.license = license;
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getLicense() {
        return license;
    }

    public String getColor() {
        return color;
    }
}
