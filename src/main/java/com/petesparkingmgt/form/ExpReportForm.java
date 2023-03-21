package com.petesparkingmgt.form;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ExpReportForm extends BaseDTO {
    @NotEmpty
    private String licensePlate;
    @NotEmpty
    private String parkingLot;
    @NotEmpty
    private String description;
    @NotEmpty
    private String imageURL;

    public ExpReportDTO getDTO() {
        ExpReportDTO report = new ExpReportDTO();
        report.setDescription(description);
        report.setImageURL(imageURL);
        report.setLicensePlate(licensePlate);
        report.setParkingLot(parkingLot);

        return report;
    }

    public void populate(ExpReportDTO report) {
        licensePlate = report.getLicensePlate();
        description = report.getDescription();
        parkingLot = report.getParkingLot();
        imageURL = report.getImageURL();
    }

}
