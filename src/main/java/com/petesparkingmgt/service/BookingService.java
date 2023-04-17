package com.petesparkingmgt.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petesparkingmgt.dao.parking.BookingDAO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import com.petesparkingmgt.dto.parking.BookingDTO;
import com.petesparkingmgt.dto.parking.SlotDTO;

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
			if (!findSlotById(dto.getSlotId()).isStatus()){
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
			slotDAO.saveAndFlush(slot);
		}



	}
}
