package com.petesparkingmgt.form;

import javax.validation.constraints.NotEmpty;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.PaymentDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentForm extends BaseDTO{
	
	@NotEmpty(message = "Card Number is required")
	private String cardNumber;
	
	@NotEmpty(message = "Name On Card is required")
	private String nameOnCard;
	
	@NotEmpty(message = "Expiry Date is required")
	private String expireDate;
	
	
	private long totalPrice;
	

	
	public PaymentDTO getDTO() {
		PaymentDTO bean=new PaymentDTO();
		bean.setId(id);
		bean.setCardNumber(cardNumber);
		bean.setNameOnCard(nameOnCard);
		bean.setExpireDate(expireDate);
		bean.setTotalPrice(totalPrice);


		return bean;
	}

	public void populate(PaymentDTO bean) {
		id = bean.getId();
		cardNumber = bean.getCardNumber();
		nameOnCard = bean.getNameOnCard();
		expireDate = bean.getExpireDate();
		totalPrice = bean.getTotalPrice();

	}
}
