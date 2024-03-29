package com.petesparkingmgt.components;

import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class BookingUpdater {

    @Autowired
    private BookingService bookingService;

    @Scheduled(fixedRate = 60000) // Run every 60 seconds (60000 milliseconds)
    public void updateExpiredBookings() {
        // Get all active bookings
        List<BookingDTO> activeBookings = bookingService.getActiveBookings();

        // Check each booking to see if it's expired
        for (BookingDTO booking : activeBookings) {
            if (isBookingExpired(booking)) {
                // Update booking status to expired
                System.out.println("Setting " + booking.getEmail() + " slot " + booking.getSlotId());
                bookingService.updateSlotStatus(booking.getId(), true);
            }
        }
        System.out.println("------------------------------------------------------------");
    }

    private boolean isBookingExpired(BookingDTO booking) {
        LocalDateTime now = LocalDateTime.now();
        if (booking.getToBookingDate() != null) {
            Instant endDateInstant = booking.getToBookingDate().toInstant();

            LocalDateTime bookingEndDate = endDateInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime bookingEndTime = LocalTime.parse(booking.getToTime(), formatter);

            LocalDateTime bookingEnd = bookingEndDate.with(bookingEndTime);

            boolean expired = now.isAfter(bookingEnd);
            // Debugging information
            System.out.println("----");
            System.out.println("User: " + booking.getEmail() + " in " + booking.getParkingName() + " expired: " + expired + "!");
            System.out.println("Now: " + now);
            System.out.println("BookingDTO end date: " + booking.getToBookingDate());
            System.out.println("bookingEndDate: " + bookingEndDate);
            System.out.println("bookingEnd: " + bookingEnd);
            System.out.println("-------");

            // Check if now is strictly after the booking end time
            return now.isAfter(bookingEnd);
        } else {
            return false;
        }
    }

}
