package com.petesparkingmgt.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FriendResponseForm {

    public FriendResponseForm(String email, String action) {
        this.email = email;
        this.action = action;
    }

    @NotEmpty(message = "Email is needed")
    private String email;

    @NotEmpty
    private String action;

    @Override
    public String toString() {
        return "FriendResponseForm [email=" + email + "]";
    }
}

