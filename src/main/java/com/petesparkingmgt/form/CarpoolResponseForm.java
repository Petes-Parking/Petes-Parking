package com.petesparkingmgt.form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CarpoolResponseForm {


    @NotEmpty(message = "Carpool name is required!")

    private String carPoolName;

    @NotEmpty
    private String action;
}
