package com.petesparkingmgt.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="user")
@Getter
@Setter
public class UserDTO extends BaseDTO {


	@Column(name = "firstName", length = 755)
	private String firstName;

	@Getter
	private String code;

	@Column(name = "lastName", length = 755)
	private String lastName;

	@Column(name = "dob")
	private String dob;

	@Column(name = "gender", length = 755)
	private String gender;


	@Column(name = "email", length = 755)
	private String email;

	@Column(name = "password", length = 755)
	private String password;


	@Column(name = "password2", length = 755)
	private String password2;

	@Column(name = "phoneNumber", length = 755)
	private String phoneNumber;

	@Column(name = "userRole", length = 755)
	private String userRole;

	@Column(name = "points")
	private int points;

	@Column(name = "level")
	private int level;

	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", userRole=" + userRole + " , points=" + points+ ", level=" + level+"]";
	}


}
