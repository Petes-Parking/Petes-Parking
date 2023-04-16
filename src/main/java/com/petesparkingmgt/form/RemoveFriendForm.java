package com.petesparkingmgt.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RemoveFriendForm {

    public RemoveFriendForm(String email, String requestSentBy) {
        this.email = email;
        this.requestSentBy = requestSentBy;
    }

    @NotEmpty(message = "Email is needed")
    private String email;

    @NotEmpty
    private String requestSentBy;

    @Override
    public String toString() {
        return "RemoveFriendForm [email=" + email + requestSentBy + "]";
    }

}

