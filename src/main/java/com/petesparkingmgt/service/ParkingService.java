package com.petesparkingmgt.service;

import java.util.List;

import com.petesparkingmgt.dao.parking.BookingDAO;
import com.petesparkingmgt.dto.BookingDTO;
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
	public BookingDAO bookingDAO;
	@Autowired
	public SlotDAO slotDao;

	public void addOccupancyData(Model model) {

		List<ParkingDTO> parkingArea = dao.findAll();

		for (ParkingDTO parking : parkingArea) {
			String name = "";
			switch ((int) parking.getId()) {
				case 1: name="corec";
				break;
				case 2: name="mccutcheon";
				break;
				case 3: name="university";
				break;
				case 4: name="rossade";
				break;
				case 5: name="northwestern";
				break;
				case 6: name="grant";


			}
			List<SlotDTO> slots = slotDao.findByParkingId(parking.getId());
			// 0 is reserved, 1 is open
			long total = parking.getNumberOfSlot();
			long occupied = slots.stream().filter(slotDTO -> !slotDTO.isStatus()).count();
			double occupancy = (double) occupied / total;
			model.addAttribute(name+"Occupancy", occupancy );
		}


	}

	public void addNameAndAdress(Model model){
		ParkingDTO corec = dao.findById(1);
		ParkingDTO mccutcheon = dao.findById(2);
		ParkingDTO university = dao.findById(3);
		ParkingDTO rossade = dao.findById(4);
		ParkingDTO northwestern = dao.findById(5);
		ParkingDTO grant = dao.findById(6);

		model.addAttribute("corecName", corec.getParkingName());
		model.addAttribute("corecAddress", corec.getAddress());

		model.addAttribute("mccutcheonName", mccutcheon.getParkingName());
		model.addAttribute("mccutcheonAddress", mccutcheon.getAddress());

		model.addAttribute("universityName", university.getParkingName());
		model.addAttribute("universityAddress", university.getAddress());

		model.addAttribute("rossadeName", rossade.getParkingName());
		model.addAttribute("rossadeAddress", rossade.getAddress());

		model.addAttribute("northwesternName", northwestern.getParkingName());
		model.addAttribute("northwesternAddress", northwestern.getAddress());

		model.addAttribute("grantName", grant.getParkingName());
		model.addAttribute("grantAddress", grant.getAddress());


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

	public BookingDTO findActiveBookingDTOwithSlotID(long slotid){
		return bookingDAO.findBySlotIdAndTakenEquals(slotid, true);
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
