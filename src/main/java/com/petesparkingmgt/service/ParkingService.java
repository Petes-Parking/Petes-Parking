package com.petesparkingmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petesparkingmgt.dao.ParkingDAO;
import com.petesparkingmgt.exception.RecordNotFoundException;
import com.petesparkingmgt.dto.ParkingDTO;
import com.petesparkingmgt.dto.SlotDTO;
import com.petesparkingmgt.dao.SlotDAO;


@Service
public class ParkingService {

	@Autowired
	public ParkingDAO dao;
	@Autowired
	public SlotDAO slotDao;

	public ParkingDTO Add(ParkingDTO dto) {
	ParkingDTO parking = null;
	parking = dao.findByParkingName(dto.getParkingName());
	System.out.println("user by email........: "+parking);
	
	if(parking != null)
		throw new RecordNotFoundException("Parking Name is already exists..");
	    System.out.println("dto Before Save: "+dto);
	    parking = dao.save(dto);
       return  parking;
	}
	

	
	public ParkingDTO findParkingById(long id) {
		return dao.findById(id);
	}
	
	public ParkingDTO findParkingByName(String parkingName) {
		return dao.findByParkingName(parkingName);
	}
	
	public List<ParkingDTO> list(){
		List<ParkingDTO> dto = dao.findAll();
		return dto;
	}
	
	public ParkingDTO update(ParkingDTO dto){
		ParkingDTO bean = dao.saveAndFlush(dto);
		return bean;
	}
	
	public void delete(long id) throws Exception {
		if(id>0)
		{
			dao.deleteById(id);
		}else {
			throw new Exception("Not a valid id");
		}
		
	}
	
	public ParkingDTO findLastParkingId() {
		return dao.findTopByOrderByIdDesc();
	}
	
	public void creatSlots(long id) {
	       ParkingDTO parkingDto = findParkingById(id);
	        long numberOfSlot = parkingDto.getNumberOfSlot();	        
	        System.out.println("numberOfSlot: "+numberOfSlot);
			for(int i=1; i<=numberOfSlot; i++) {
				SlotDTO slots = new SlotDTO();
				slots.setParkingId(parkingDto.getId());
				slots.setSlot(i);
				slots.setStatus(true);
				slotDao.save(slots);
			}
	}
	
}
