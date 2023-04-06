package com.petesparkingmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petesparkingmgt.dao.parking.ParkingDAO;
import com.petesparkingmgt.exception.RecordNotFoundException;
import com.petesparkingmgt.dto.parking.ParkingDTO;
import com.petesparkingmgt.dto.parking.SlotDTO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import org.springframework.ui.Model;


@Service
public class ParkingService {

	@Autowired
	public ParkingDAO dao;
	@Autowired
	public SlotDAO slotDao;

	public void addOccupancyData(Model model) {

		List<ParkingDTO> parkingArea = dao.findAll();

		for (ParkingDTO parking : parkingArea) {
			String name = "";
			switch (parking.getParkingName()) {
				case "Corec Parking Lot": name="corec";
				break;
				case "McCutcheon Drive Parking Garage": name="mccutcheon";
				break;
				case "University St. Parking Garage": name="university";
				break;
				case "Ross-Ade Stadium Parking Lot": name="rossade";
				break;
				case "Northwestern St. Parking Garage": name="northwestern";
				break;
				case "Grant St. Parking Garage": name="grant";


			}
			List<SlotDTO> slots = slotDao.findByParkingId(parking.getId());
			// 0 is reserved, 1 is open
			long total = parking.getNumberOfSlot();
			long occupied = slots.stream().filter(slotDTO -> !slotDTO.isStatus()).count();
			double occupancy = (double) occupied / total;
			System.out.println("Occupied: " + occupied + " for " + name + " with value " + occupancy);
			model.addAttribute(name+"Occupancy", occupancy );
		}


	}

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
