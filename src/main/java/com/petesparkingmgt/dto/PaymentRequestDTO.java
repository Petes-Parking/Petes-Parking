package com.petesparkingmgt.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="paymentrequest")
@Getter
@Setter
public class PaymentRequestDTO extends BaseDTO {
	
	 @Column(name = "userID")
	 private long userID;

	 @Column(name = "amount")
	 private long amount;
	 
	 @Column(name = "status")
	 private String status;

	@Override
	public String toString() {
		return "PaymentRequestDTO [userID=" + userID + ", amount=" + amount + ", status=" + status + "]";
	}
	 
	 
	 
}
