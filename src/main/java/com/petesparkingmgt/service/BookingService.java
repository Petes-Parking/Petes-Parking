package com.petesparkingmgt.service;

import java.util.List;

import com.petesparkingmgt.points.PointsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petesparkingmgt.dao.BookingDAO;
import com.petesparkingmgt.dao.SlotDAO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.ParkingDTO;
import com.petesparkingmgt.dto.SlotDTO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.exception.RecordNotFoundException;
import com.petesparkingmgt.form.BookingForm;
import com.petesparkingmgt.service.BookingService;
import com.petesparkingmgt.service.ParkingService;

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
	
	public BookingDTO findBookingById(long id) {
		return dao.findById(id);
	}
	
	public BookingDTO update(BookingDTO dto) {
		
		return dao.saveAndFlush(dto);
	}

}
