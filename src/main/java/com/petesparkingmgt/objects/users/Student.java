package com.petesparkingmgt.objects.users;

import com.petesparkingmgt.objects.DateQueue;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * @author maxfuligni
 * @date 3/1/23
 *
 * Main class used for data object actions involving users of the app. All users are students, even if they're not actually
 * a student. Contains points and a history used for dynamic memory before being saved to database.
 */
public class Student extends User {


    @Getter
    private DateQueue history;
    @Getter
    @Setter
   private  int points;

    @Getter
    private List<String> vehicles;




    public Student(String email, String password, String gender, String dateOfBirth, String firstName, String lastName, String phoneNumber, DateQueue history, int points) {
        super(email, password, gender, dateOfBirth, firstName, lastName, phoneNumber);
        this.history = new DateQueue();
        this.points = 0;
    }

    public void updatePoints(int amount) {
        this.points += amount;
    }

    /**
     * Vehicles are essentially just strings. Not super necessary to create an entire class for just vehicles, at least for now.
     * Limit is 2 vehicles
     * @param info of car to store.
     */
    public void addVehicle(String info) throws Exception {
        if (vehicles.size() > 2){
            throw new Exception("Too many vehicles!");
        }
        vehicles.add(info);
    }

    // At some point, may create a Booking object to store the parking area, but for now, just the
    // date is important
   public void addNewBooking(LocalDate date) {
        getHistory().insert(date);
   }

    @Override
    public UserType getType() {
        return UserType.STUDENT;
    }
}
