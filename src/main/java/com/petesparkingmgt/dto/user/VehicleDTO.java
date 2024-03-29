package com.petesparkingmgt.dto.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="vehicles")
@Getter
@Setter
public class VehicleDTO extends BaseDTO {


    @Column(name="user_id")
    private long userId;
    @Column(name = "make1", length = 755)
    private String make1;


    @Column(name = "model1", length = 755)
    private String model1;

    @Column(name = "color1")
    private String color1;

    @Column(name = "license1", length = 755)
    private String license1;

    @Column(name = "make2", length = 755)
    private String make2;

    @Column(name = "model2", length = 755)
    private String model2;

    @Column(name = "color2", length = 755)
    private String color2;

    @Column(name = "license2", length = 755)
    private String license2;
    @Column(name = "permitA", length = 755)
    private String permitA;
    @Column(name = "permitB", length = 755)
    private String permitB;
    @Column(name = "permitC", length = 755)
    private String permitC;
    @Column(name = "permitCG", length = 755)
    private String permitCG;
    @Column(name = "permitR", length = 755)
    private String permitR;
    @Column(name = "permitD", length = 755)
    private String permitD;
    @Override
    public String toString() {
        return "VehicleDTO [make1=" + make1 + ", model1=" + model1 + ", color1=" + color1 + ", license1=" + license1
                + ", make2=" + make2 + ", model2=" + model2 + ", color2="
                + color2 + ", license2=" + license2 + "]";
    }




}

