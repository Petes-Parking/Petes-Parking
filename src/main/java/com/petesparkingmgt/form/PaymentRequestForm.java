package com.petesparkingmgt.form;

import javax.validation.constraints.NotEmpty;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.PaymentRequestDTO;
import com.petesparkingmgt.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestForm extends BaseDTO{

	
	
	private String userId;
	
	@NotEmpty(message = "amount is required")
	private String amount;
	
	
	public PaymentRequestDTO getDTO() {
		PaymentRequestDTO bean=new PaymentRequestDTO();
		bean.setId(id);
		bean.setUserID(DataUtility.getLong(userId));
	    bean.setAmount(DataUtility.getLong(amount));

		return bean;
	}

	public void populate(PaymentRequestDTO bean) {
		id = bean.getId();
		amount = DataUtility.getStringData(bean.getAmount());
		userId = DataUtility.getStringData(bean.getUserID());

	}
}
