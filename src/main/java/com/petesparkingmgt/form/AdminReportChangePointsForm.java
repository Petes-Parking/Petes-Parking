package com.petesparkingmgt.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AdminReportChangePointsForm {



    @NotEmpty(message = "Email is needed")
    private String reportedEmail;

    @Override
    public String toString() {
        return "AdminReportChangePointsForm [reportedEmail=" + reportedEmail + "]";
    }

}

