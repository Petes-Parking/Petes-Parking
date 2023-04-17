package com.petesparkingmgt.form;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.user.VehicleDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleForm extends BaseDTO {

    private String make1;


    private String model1;

    private String color1;

    private String license1;

    private String make2;

    private String model2;

    private String color2;

    private String license2;

    private String permitA;
    private String permitB;
    private String permitC;
    private String permitD;

    public VehicleDTO getDTO() {
        VehicleDTO bean=new VehicleDTO();
        bean.setId(id);
        bean.setMake1(make1);
        bean.setModel1(model1);
        bean.setColor1(color1);
        bean.setLicense1(license1);
        bean.setMake2(make2);
        bean.setModel2(model2);
        bean.setColor2(color2);
        bean.setLicense2(license2);
        bean.setPermitA(permitA);
        bean.setPermitA(permitB);
        bean.setPermitA(permitC);
        bean.setPermitA(permitD);

        return bean;
    }
}
