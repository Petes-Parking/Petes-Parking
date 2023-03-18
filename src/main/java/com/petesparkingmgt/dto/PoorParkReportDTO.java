package com.petesparkingmgt.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="poorpark_report")
@Getter
@Setter
public class PoorParkReportDTO extends BaseDTO {

    @Column(name = "description", length = 755)
    private String description;

    @Column(name = "license", length = 755)
    private String licensePlate;

    @Column(name = "reporterEmail", length = 755)
    private String reporterEmail;

    @Column(name = "imageURL", length = 755)
    private String imageURL;

    @Column(name = "parkingLot", length = 755)
    private String parkingLot;

    @Column(name = "poorpark_reportID")
    private int exp_reportID;

    @Column(name = "reportDate")
    private Date reportDate;

    @Override
    public String toString() {
        return "PoorParkReportDTO [licensePlate=" + licensePlate + ", parkingLot=" + parkingLot + ", description=" + description + ", imageURL="
                + imageURL + ", reporterEmail=" + reporterEmail + ", reportDate=" + reportDate + "]";
    }




}
