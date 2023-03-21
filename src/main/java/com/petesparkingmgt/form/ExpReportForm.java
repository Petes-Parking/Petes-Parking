package com.petesparkingmgt.form;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;

public class ExpReportForm extends BaseDTO {
    private String licensePlate;
    private String parkingLot;
    private String description;
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
