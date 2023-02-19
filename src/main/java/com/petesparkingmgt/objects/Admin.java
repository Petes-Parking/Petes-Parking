package com.petesparkingmgt.objects;




/**
 * @author Max Fuligni
 * @date 2/16/23
 *
 * Admin type to distinctify between admins
 */
public class Admin extends User {
    /**
     * @param email
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @format mm/dd/yyyy
     **/
    public Admin(String email, String firstName, String lastName, String dateOfBirth) {
        super(email, firstName, lastName, dateOfBirth);
    }
}
