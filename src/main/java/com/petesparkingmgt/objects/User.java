package com.petesparkingmgt.objects;


/**
 * @author Max Fuligni
 * @date 2/16/23
 *
 * Base user class for storing all users (admins and students)
 * Stores all basic fields
 */
public class User {

    private String email, password;
    private String firstName, lastName;



    private String dateOfBirth; /** @format mm/dd/yyyy **/


    public User(String email, String firstName, String lastName, String dateOfBirth) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
