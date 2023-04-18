package com.petesparkingmgt.dto.parking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.petesparkingmgt.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="parking")
@Getter
@Setter
public class ParkingDTO extends BaseDTO {
	
	@Column(name = "parkingName", length = 755)
	private String parkingName;

	@Column(name = "address", length = 755)
	private String address;
	
	@Column(name = "numberOfSlot")
	private long numberOfSlot;
	@Column(name = "permitA")
	private String permitA;
	@Column(name = "permitB")
	private String permitB;
	@Column(name = "permitC")
	private String permitC;
	@Column(name = "permitCG")
	private String permitCG;
	@Column(name = "permitR")
	private String permitR;
	@Column(name = "permitD")
	private String permitD;
	

}
