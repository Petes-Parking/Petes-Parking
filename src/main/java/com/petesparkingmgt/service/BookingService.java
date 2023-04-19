package com.petesparkingmgt.service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.petesparkingmgt.dao.parking.BookingDAO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.parking.SlotDTO;
import com.petesparkingmgt.utility.DataUtility;

@Service
public class BookingService {
	
	
	@Autowired
	public BookingDAO dao;

	@Autowired
	public SlotDAO slotDAO;

	public BookingDTO Add(BookingDTO dto) {
	    System.out.println("Requested Slot Number : "+dto.getSlot()+ " Requested Slot Id: "+dto.getSlotId());
	    System.out.println("DTO before Save: "+dto);
	    
	    BookingDTO booking = dao.save(dto);	   
	    
	    System.out.println("Booked Slot id: "+booking.getSlotId());
	    System.out.println("Booking After Save: "+booking);

	    SlotDTO slot = slotDAO.findById(booking.getSlotId());
	    slot.setStatus(false);
		dto.setTaken(true);
	    slotDAO.saveAndFlush(slot);
       return  booking;
	}
	
	public List<BookingDTO> list(){
		List<BookingDTO> dto = dao.findAll();
		return dto;
	}
	
	public List<BookingDTO> findBookingByEmail(String email){
		List<BookingDTO> dto = dao.findByEmail(email);
		return dto;
	}
	
	public SlotDTO findSlotById(long id){
		SlotDTO dto = slotDAO.findById(id);
		return dto;
	}

	public int calculatePoints(BookingDTO bookingDetails) {
		Date bookingDate = bookingDetails.getFromBookingDate();
		LocalDate currentDate = LocalDate.now();
		long daysDifference = ChronoUnit.DAYS.between(currentDate, convertDateToLocalDate(bookingDate));

		int basePoints = 10;
		int additionalPointsPerDay = 20;
		return basePoints + (int) (additionalPointsPerDay * daysDifference);

	}

	private LocalDate convertDateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}


	public BookingDTO findBookingById(long id) {
		return dao.findById(id);
	}
	
	public BookingDTO update(BookingDTO dto) {
		
		return dao.saveAndFlush(dto);
	}


    public List<BookingDTO> getActiveBookings() {
		List<BookingDTO> bookingDTOList = dao.findAll();
		List<BookingDTO> activeBookings = new ArrayList<>();


		for (BookingDTO dto : bookingDTOList) {
			if (dto.isTaken()){
				activeBookings.add(dto);
			}
		}
		return activeBookings;
    }

	public void updateSlotStatus(long bookingId, boolean isAvalible) {
		BookingDTO toChange = dao.findById(bookingId);
		if (toChange != null) {
			SlotDTO slot = slotDAO.findById(toChange.getSlotId());
			slot.setStatus(isAvalible);
			toChange.setTaken(false);
			update(toChange);
			slotDAO.saveAndFlush(slot);
		}

	}

	
	// update upon time reguest
//	@Scheduled(fixedDelay = 60000)
//	 public void scheduleFixedDelayTask() {
//	     List<BookingDTO> bookingList = dao.findAll();     
//	     List<SlotDTO> slotList = slotDAO.findAll();
//	     
//	     for (BookingDTO bookingDTO : bookingList) {
//	      
//	      long slot_id = bookingDTO.getSlotId();
//	      String toBookingDate = DataUtility.getDateString1(bookingDTO.getToBookingDate());
//	      
//	      String toTime = bookingDTO.getToTime();
//	      String finalBookingTime = toBookingDate+" "+toTime+":00";
//	      System.out.println("To Booking Date and time: "+finalBookingTime);
//	      System.out.println("Current Time: "+DataUtility.getCurrentTimestamp());
//	      
//	      int b3 = DataUtility.getCurrentTimestamp().compareTo(DataUtility.getDateAndTime(finalBookingTime));  
//	      System.out.println("Compare Status: "+b3);
//	      
//	      SlotDTO slotDTO = slotDAO.findById(slot_id);
//	      
//	      if(slotDTO.isStatus()) {
//	       //System.out.println("Current Time: "+DataUtility.getCurrentTimestamp());
//	      }else if(b3>=0){   
//	       slotDTO.setStatus(true);
//	       slotDAO.saveAndFlush(slotDTO);
//	      }
//	   
//	  }
//	
//	}

	public boolean isBookingDurationValid(BookingDTO booking) {
		Instant startDateInstant = booking.getFromBookingDate().toInstant();
		LocalDateTime bookingStartDate = startDateInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();

		Instant endDateInstant = booking.getToBookingDate().toInstant();
		LocalDateTime bookingEndDate = endDateInstant.atZone(ZoneId.systemDefault()).toLocalDateTime();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime bookingStartTime = LocalTime.parse(booking.getFromTime(), formatter);
		LocalTime bookingEndTime = LocalTime.parse(booking.getToTime(), formatter);

		LocalDateTime bookingStart = bookingStartDate.with(bookingStartTime);
		LocalDateTime bookingEnd = bookingEndDate.with(bookingEndTime);

		Duration bookingDuration = Duration.between(bookingStart, bookingEnd);
		long bookingDurationInHours = bookingDuration.toHours();

		// Check if the booking duration is more than 10 hours
		if (bookingDurationInHours > 10) {
			System.out.println("The maximum duration you can book is 10 hours.");
			return false;
		}

		return true;
	}
	



}
