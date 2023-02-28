package com.petesparkingmgt.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	

}
