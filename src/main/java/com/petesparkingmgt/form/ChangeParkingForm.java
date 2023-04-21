package com.petesparkingmgt.form;

import javax.validation.constraints.NotEmpty;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.parking.ParkingDTO;
import com.petesparkingmgt.utility.DataUtility;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeParkingForm extends BaseDTO{

    @NotEmpty(message = "Parking name is required")
    private String parkingName;

    @NotEmpty(message = "Address is required")
    private String address;



    private int parkingId;







}
