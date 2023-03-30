package com.petesparkingmgt.form;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.ExpReportDTO;
import com.petesparkingmgt.dto.PoorParkReportDTO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PoorParkingReportForm extends BaseDTO {
    @NotEmpty
    private String licensePlate;
    @NotEmpty
    private String parkingLot;
    @NotEmpty
    private String description;

    public PoorParkReportDTO getDTO() {
        PoorParkReportDTO report = new PoorParkReportDTO();
        report.setDescription(description);
        report.setLicensePlate(licensePlate);
        report.setParkingLot(parkingLot);

        return report;
    }

    public void populate(PoorParkReportDTO report) {
        licensePlate = report.getLicensePlate();
        description = report.getDescription();
        parkingLot = report.getParkingLot();
    }

}
