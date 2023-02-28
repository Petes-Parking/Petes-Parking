package com.petesparkingmgt.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="slots")
@Getter
@Setter
public class SlotDTO extends BaseDTO {
	
   @Column(name = "slot")
   private long slot;
   
   @Column(name = "parkingId")
   private long parkingId;
   
   @Column(name = "status")
   private boolean status;

}
