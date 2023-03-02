package com.petesparkingmgt.objects.users;


/**
 * @author maxfuligni
 * @date 3/1/23
 *
 * Moreso a declaration to distinguish among Admin users. Can set up views here
 * to show Admin-specific views later.
 */
public class Admin extends User {

    public Admin(String email, String password, String gender, String dateOfBirth, String firstName, String lastName, String phoneNumber) {
        super(email, password, gender, dateOfBirth, firstName, lastName, phoneNumber);
    }

    @Override
    public UserType getType() {
        return UserType.ADMIN;
    }
}
