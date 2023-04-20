package com.petesparkingmgt.ctl;

import java.util.Base64;
import java.util.List;

import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.petesparkingmgt.form.ParkingForm;
import com.petesparkingmgt.service.ParkingService;
import com.petesparkingmgt.dto.parking.ParkingDTO;
import com.petesparkingmgt.dto.parking.SlotDTO;
import com.petesparkingmgt.dao.parking.SlotDAO;

import javax.servlet.http.HttpSession;


@Controller
public class ParkingCtl {
	
	@Autowired
	public ParkingService service;

	@Autowired
	public BookingService bookingService;
	
	@Autowired
	public SlotDAO slotDao;

	@Autowired
	public UserDAO userDAO;
	
	
	@GetMapping("/parking")
	public String parking(@ModelAttribute("form")ParkingForm form, Model model) {
		return "parking";
	}
	

	@GetMapping("/parkinglist")
	public String list(@ModelAttribute("form")ParkingForm form, Model model, HttpSession session){
		List<ParkingDTO> list = service.list();
		model.addAttribute("list", list);
		UserDTO user = (UserDTO) session.getAttribute("user");
		byte[] imageData = user.getProfilePicture();
		String base64Image = Base64.getEncoder().encodeToString(imageData);
		model.addAttribute("profilePic", base64Image);
	return "parkinglist";
		
	}
	

	
	long parkingId = 0;
	@GetMapping("/viewparkingSlot")	
	public String viewSlot(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ) throws Exception{
		parkingId = id;
		List<SlotDTO> slots = slotDao.findByParkingId(id);
        model.addAttribute("list", slots);
		return "viewslots";
	}
	
	@GetMapping("/updateslot")	
	public String updateSlot(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ) throws Exception{
		SlotDTO slot = slotDao.findById(id);
		slot.setStatus(true);

		// need to find any bookingDTO associated with this slot and set taken to false
		BookingDTO correspondingBooking = service.findActiveBookingDTOwithSlotID(id);
		if (correspondingBooking != null) {
			correspondingBooking.setTaken(false);
			bookingService.update(correspondingBooking);
		}
		slotDao.saveAndFlush(slot);
		System.out.println("parkingId: "+parkingId);
		 List<SlotDTO> slots = slotDao.findByParkingId(parkingId);
	     model.addAttribute("list", slots);
		return "viewslots";
	}
	
	@GetMapping("/parkingEdit")	
	public String update(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ){
		ParkingDTO bean = service.findParkingById(id);
		form.populate(bean);
		model.addAttribute("bean",bean);	
		return "parking";
	}
	
	@GetMapping("/parkingDelete")	
	public String delete(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ) throws Exception{
		service.delete(id);	
		
		List<ParkingDTO> list =	service.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "Parking Deleted successfully");
		return "parkinglist";
	}
	

	
	
	
	
}
