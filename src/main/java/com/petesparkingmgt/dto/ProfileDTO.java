package com.petesparkingmgt.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="profile")
@Getter
@Setter
public class ProfileDTO extends BaseDTO {


    @Column(name = "make1", length = 755)
    private String make1;

//    @Getter
//    private String code;

    @Column(name = "model1", length = 755)
    private String model1;

    @Column(name = "color1")
    private String color1;

    @Column(name = "license1", length = 755)
    private String license1;

//
//    private String password2;

    @Column(name = "make2", length = 755)
    private String make2;

    @Column(name = "model2", length = 755)
    private String model2;

    @Column(name = "color2", length = 755)
    private String color2;

    @Column(name = "license2", length = 755)
    private String license2;

//    @Column(name = "points")
//    private int points;

//    @Column(name = "booked_date1")
//    private String bdate1;
//
//    @Column(name = "booked_date2")
//    private String bdate2;
//
//    @Column(name = "booked_date3")
//    private String bdate3;

    @Override
    public String toString() {
        return "ProfileDTO [make1=" + make1 + ", model1=" + model1 + ", color1=" + color1 + ", license1=" + license1
                + ", make2=" + make2 + ", model2=" + model2 + ", color2="
                + color2 + ", license2=" + license2 + "]";
    }




}

