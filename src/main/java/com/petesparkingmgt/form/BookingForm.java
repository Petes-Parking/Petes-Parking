package com.petesparkingmgt.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingForm extends BaseDTO {

	@NotEmpty(message = "First name is required")
	private String firstName;

	@NotEmpty(message = "Last name is required")
	private String lastName;
	
	@NotEmpty(message = "First name is required")
	private String email;
	
	@NotEmpty(message = "First name is required")
	private String phoneNumber;
	
	@NotEmpty(message = "First name is required")
	private String parkingName;
	
	@NotEmpty(message = "First name is required")
	private String slotId;
	
	@NotEmpty(message = "First name is required")
	private String fromBookingDate;
	
	@NotEmpty(message = "First name is required")
	private String toBookingDate;
	
	@NotEmpty(message = "First name is required")
	private String fromTime;
	
	@NotEmpty(message = "First name is required")
	private String toTime;
	
	private String userId;
	
	
	

	public BookingDTO getDTO() {
		
		BookingDTO bean=new BookingDTO();
		
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setEmail(email);
		bean.setPhoneNumber(phoneNumber);
		bean.setParkingName(parkingName);
		bean.setSlotId(DataUtility.getLong(slotId));
		bean.setFromBookingDate(DataUtility.getDate1(fromBookingDate));
		bean.setToBookingDate(DataUtility.getDate1(toBookingDate));
		bean.setFromTime(fromTime);
		bean.setToTime(toTime);
		
		bean.setUserId(DataUtility.getLong(userId));
	
		
		

		return bean;
	}

	public void populate(BookingDTO bean) {
		
		id = bean.getId();
		firstName=bean.getFirstName();
		lastName=bean.getLastName();
		email = bean.getEmail();
		phoneNumber = bean.getPhoneNumber();
		parkingName = bean.getParkingName();
		slotId = DataUtility.getStringData(bean.getSlotId());
		fromBookingDate = DataUtility.getStringData(bean.getFromBookingDate());
		toBookingDate = DataUtility.getStringData(bean.getToBookingDate());
		fromTime = bean.getFromTime();
		toTime = bean.getToTime();
       
        userId = DataUtility.getStringData(bean.getUserId());
      
	}

	@Override
	public String toString() {
		return "BookingForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", parkingName=" + parkingName + ", slotId=" + slotId + ","
						+ " fromBookingDate=" + fromBookingDate + ", toBookingDate=" + toBookingDate + ","
								+ " fromTime=" + fromTime + ", toTime=" + toTime + ", userId=" + userId + "]";
	}



	
}
