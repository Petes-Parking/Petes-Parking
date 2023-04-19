package com.petesparkingmgt.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.petesparkingmgt.dto.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="payment")
@Getter
@Setter
public class PaymentDTO extends BaseDTO {

	@Column(name = "cardNumber", length = 755)
	private String cardNumber;
	
	@Column(name = "nameOnCard", length = 755)
	private String nameOnCard;
	
	@Column(name = "expireDate", length = 755)
	private String expireDate;
	
	@Column(name = "email", length = 755)
	private String email;
	
	@Column(name = "totalPrice")
	private long totalPrice;

	@Override
	public String toString() {
		return "PaymentDTO [cardNumber=" + cardNumber + ", nameOnCard=" + nameOnCard + ", expireDate=" + expireDate
				+ ", email=" + email + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
}