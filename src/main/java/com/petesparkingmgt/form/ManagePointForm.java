package com.petesparkingmgt.form;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagePointForm extends BaseDTO {

    private int amount;
    private String type;
    private long userId;


    @Override
    public String toString() {
        return "ManagePointForm [id=" + id + ", action=" + type + ", amount=" + amount + "]";

    }
}
