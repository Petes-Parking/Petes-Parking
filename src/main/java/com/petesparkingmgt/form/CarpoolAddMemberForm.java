package com.petesparkingmgt.form;

import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class CarpoolAddMemberForm {


    public CarpoolAddMemberForm(String email) {
        this.email = email;
    }


    @NotEmpty(message = "Email is needed")
    private String email;





    @Override
    public String toString() {
        return "CarPoolAddMemberForm [email=" + email + "]";
    }

}
