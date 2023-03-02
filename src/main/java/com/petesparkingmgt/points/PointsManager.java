package com.petesparkingmgt.points;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
@author maxfuligni
 @date 2/28/23

 The equation for distributing points will be 20 / (days between current date and last 3 bookings)

 For example, if a student booked on a monday, tuesday, and then a friday, and is now booking on saturday, the equation would be
 20 / (4 + 3 + 1) = 2.5 points rewarded.

 The highest amount of points that could be distributed would be 20 / (1+1+1) = 6.66667
 which would be rounded up to 7 points. This incentives the user to use the app more frequently.

 If a student has less than 3 bookings, the method will use as many bookings are availible. If the user has never
 booked a spot before, assigns the full base points



 This class is a static singleton meant for delegating points
 */
public class PointsManager {

    @Getter
    @Setter
    static int base = 20; /* Can scale base by increasing or decreasing this value to adjust our point economy */

    public static int getPointsForHistory(List<LocalDate> lastThreeBookings){
        if(lastThreeBookings.isEmpty()){
            return base;
        }

        long[] differences = new long[3];
        int i = 0;


        for (LocalDate date : lastThreeBookings) {

            long diff = ChronoUnit.DAYS.between(date, LocalDateTime.now()); // Calculation is in DAYS
            differences[i] = diff;
            i++;
            if(i > 2) break;
        }
        return (int) Math.ceil((int) (base / (sum(differences))));

    }

    private static int sum(long[] differences) {
        int total = 0;
        for (long j : differences){
            total += j;
        }
        return total;
    }


}
