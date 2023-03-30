package com.petesparkingmgt.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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

    @Column(name = "licensePlate", length = 755)
    private String licensePlate;

    @Column(name = "reporterEmail", length = 755)
    private String reporterEmail;

    @Lob
    @Column(name = "imageData")
    private byte[] imageData;

    @Column(name = "parkingLot", length = 755)
    private String parkingLot;

    @Column(name = "reportDate")
    private Date reportDate;

    @Override
    public String toString() {
        return "PoorParkReportDTO [licensePlate=" + licensePlate + ", parkingLot=" + parkingLot + ", description=" + description +
                 ", reporterEmail=" + reporterEmail + ", reportDate=" + reportDate + "]";
    }




}
