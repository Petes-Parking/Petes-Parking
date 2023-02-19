package com.petesparkingmgt.objects;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
@author Max Fuligni
@date 2/16/23

Our main class to encapsulate our student class,
* */
public class Student extends User {

    Image profilePicture;
    List<Vehicle> vehicleList;
    int points;


    public Student(String email, String firstName, String lastName, String dateOfBirth) {
        super(email, firstName, lastName, dateOfBirth);
        this.profilePicture = null;
        this.vehicleList = new ArrayList<>();
        this.points = 0;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void removePoints(int points) {
        this.points -= points;
    }
}
