package com.petesparkingmgt.form;


import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import com.petesparkingmgt.dto.BaseDTO;
import com.petesparkingmgt.dto.user.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm extends BaseDTO {

	@NotEmpty(message = "First name is required")
	private String firstName;

	private String referralCode;

	@NotEmpty(message = "Last name is required")
	private String lastName;

	@NotEmpty(message = "DOB name is required")
	private String dob;

	@NotEmpty(message = "First name is required")
	private String gender;

	@NotEmpty(message = "First name is required")
	private String email;

	@NotEmpty(message = "First name is required")
	private String password;

	@NotEmpty(message = "Password is required")
	private String password2;

	@NotEmpty(message = "First name is required")
	private String phoneNumber;

	@NotEmpty(message = "userRole is required")
	private String userRole;

	private int points;

	private String bdate1;
	private String bdate2;
	private String bdate3;




	public UserDTO getDTO() {
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setDob(dob);
		bean.setGender(gender);
		bean.setEmail(email);
		bean.setPassword(password);
		bean.setPhoneNumber(phoneNumber);
		bean.setUserRole(userRole);
		bean.setPoints(points);
		bean.setPassword2(password2);
		bean.setReferralCodeUsed(referralCode);




		return bean;
	}

	public void populate(UserDTO bean) {
		id = bean.getId();
		firstName=bean.getFirstName();
		lastName=bean.getLastName();
		dob = bean.getDob();
		gender = bean.getGender();
		email = bean.getEmail();
		password = bean.getPassword();
		phoneNumber = bean.getPhoneNumber();
		userRole = bean.getUserRole();
		points = bean.getPoints();



	}

	@Override
	public String toString() {
		return "UserForm [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", points= " + points + "]";
	}









}