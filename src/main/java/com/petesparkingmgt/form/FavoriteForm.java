package com.petesparkingmgt.form;

import javax.validation.constraints.NotEmpty;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.FavoriteDTO;
import com.petesparkingmgt.dto.UserDTO;

import com.petesparkingmgt.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteForm extends BaseDTO {

    @NotEmpty(message = "First name is required")
    private String userId;

    @NotEmpty(message = "Last name is required")
    private String area;



    public FavoriteDTO getDTO() {

        FavoriteDTO bean=new FavoriteDTO();

        bean.setId(id);
        bean.setArea(area);
        return bean;
    }

    public void populate(FavoriteDTO bean) {
        id = bean.getId();
        area=bean.getArea();
    }

    @Override
    public String toString() {
        return "FavoriteForm [userId=" + userId + ", area=" + area + "]";
    }




}
