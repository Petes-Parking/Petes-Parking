package com.petesparkingmgt.dto;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pending_users")
@Getter
@Setter
public class PendingUserDTO extends BaseDTO {


    @Column(name = "firstName", length = 755)
    private String firstName;



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





    @Override
    public String toString() {
        return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender
                + ", email=" + email + ", password=" + password + ", phoneNumber="
                + phoneNumber + ", userRole=" + userRole + "]";
    }


}
