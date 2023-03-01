package com.petesparkingmgt.objects.users;


import lombok.Getter;
import lombok.Setter;

/**
 * @author maxfuligni
 * @date 3/1/23
 *
 * Wrapper class for UserDTO, primarily a super class for Student class.
 */
public abstract class User {

    @Getter
    @Setter
    private String email, password, gender, phoneNumber;

    @Getter
    private final String dateOfBirth, firstName, lastName;



    public User(String email, String password, String gender, String dateOfBirth, String firstName, String lastName,
                String phoneNumber) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String firstName() {
        return getFirstName() + " " + getLastName();
    }

    public abstract UserType getType();
}
