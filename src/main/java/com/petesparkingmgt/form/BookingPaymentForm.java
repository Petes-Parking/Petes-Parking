package com.petesparkingmgt.form;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.utility.DataUtility;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BookingPaymentForm extends BaseDTO {

    private int cost;

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
    private String slotId;;

    @NotEmpty(message = "First name is required")
    private String fromBookingDate;

    @NotEmpty(message = "First name is required")
    private String toBookingDate;

    @NotEmpty(message = "First name is required")
    private String fromTime;

    @NotEmpty(message = "First name is required")
    private String toTime;



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

        return bean;
    }


    public void setDTO(BookingDTO dto) {

       firstName = dto.getFirstName();
       lastName = dto.getLastName();
       email = dto.getEmail();
       phoneNumber = dto.getPhoneNumber();
       parkingName = dto.getParkingName();
       slotId = String.valueOf(dto.getSlotId());
       fromBookingDate = String.valueOf(dto.getFromBookingDate());
       toBookingDate = String.valueOf(dto.getToBookingDate());
       fromTime = dto.getFromTime();
       toTime = dto.getToTime();

    }


    @Override
    public String toString() {
        return "BookingPaymentForm [cost=" + cost + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
                + phoneNumber + ", parkingName=" + parkingName + ", slotId=" + slotId + ", fromBookingDate="
                + fromBookingDate + ", toBookingDate=" + toBookingDate + ", fromTime=" + fromTime + ", toTime=" + toTime
                + "]";
    }

}
