package com.petesparkingmgt.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AddFriendForm {

    public AddFriendForm(String email) {
        this.email = email;
    }



    @NotEmpty(message = "Email is needed")
    private String email;

    @Override
    public String toString() {
        return "AddFriendForm [email=" + email + "]";
    }

}

