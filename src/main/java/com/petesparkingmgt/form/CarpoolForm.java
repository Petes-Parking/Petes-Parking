package com.petesparkingmgt.form;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CarpoolForm extends BaseDTO {

    @NotEmpty(message = "Carpool name is required!")
    private String carPoolName;

   // private long leaderId;

    public CarpoolDTO getDTO() {
        CarpoolDTO bean = new CarpoolDTO();
        if (this.carPoolName == null){
            System.out.println("Name is null!");
            return null;
         }
        bean.setCarPoolName(this.carPoolName);
       // bean.setLeaderId(leaderId);
        return bean;
    }

    public void populate(CarpoolDTO bean) {
      // leaderId = bean.getLeaderId();
       carPoolName = bean.getCarPoolName();
       id = bean.getId();



    }

    @Override
    public String toString() {
        return "CarpoolForm [id=" + id + ", carPoolName=" + carPoolName + ", leaderid=" +  "-1]";
    }


}
