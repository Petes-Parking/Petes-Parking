package com.petesparkingmgt.ctl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import com.petesparkingmgt.dao.HistoryDAO;
import com.petesparkingmgt.dto.*;
import com.petesparkingmgt.service.CarpoolService;
import com.petesparkingmgt.service.CarpoolUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petesparkingmgt.dao.BookingDAO;

import com.petesparkingmgt.dao.SlotDAO;
import com.petesparkingmgt.exception.RecordNotFoundException;
import com.petesparkingmgt.form.BookingForm;
import com.petesparkingmgt.service.BookingService;
import com.petesparkingmgt.service.ParkingService;



@Controller
public class BookingCtl {

     @Autowired
     public SlotDAO slotDAO;
     
     @Autowired
     public BookingDAO bookingDAO;
     
     @Autowired
     public BookingService service;
     
     @Autowired
     public ParkingService parkingService;

	 @Autowired
	 public CarpoolUsersService carpoolUsersService;

	 @Autowired
	 public CarpoolService carpoolService;

	@Autowired
	public HistoryDAO historyDAO;


	
	@GetMapping("/booking")
	public String booking(@ModelAttribute("form")BookingForm form, Model model, @RequestParam("id") long id, HttpSession session) {
		UserDTO user = (UserDTO)session.getAttribute("user");


		ParkingDTO parkingDTO = parkingService.findParkingById(id);
		List<SlotDTO> slotList = slotDAO.findByParkingIdAndStatus(id, true);		
		model.addAttribute("slotList", slotList);
		model.addAttribute("user", user);
		model.addAttribute("parkingDTO", parkingDTO);
		
		return "booking";
	}
	
	@PostMapping("/addBooking")
	public String Add(@Valid @ModelAttribute("form")BookingForm form,  BindingResult bindingResult, Model model, HttpSession session) {

		UserDTO user = (UserDTO)session.getAttribute("user");

		System.out.println("form: "+form);
		try {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult : "+bindingResult);
			return "booking";
		}else {
			if (carpoolUsersService.isInCarpoolButNotLeader(user.getId())){
				// do not allow them to book, only the leader can book
				model.addAttribute("error", "Only the carpool leader can reserve a slot!");
				return "booking";
			} else if (carpoolUsersService.isLeader(user.getId()) && carpoolService.hasReservation(carpoolUsersService.getCarpoolFor(user.getId()).getCarpoolId())){
				// they are a leader but there is currently a reservation, can only have one at a time
				model.addAttribute("error", "You can only book one reservation per carpool at a time!");
				return "booking";
			}
			BookingDTO bean = form.getDTO();

			HistoryDTO historyBean = new HistoryDTO();
			historyBean.setUserId(user.getId());
			historyBean.setArea(form.getParkingName());
			historyBean.setDate(form.getFromBookingDate());
			historyDAO.save(historyBean);

			bean.setId(0);
		    SlotDTO slotDTO =	slotDAO.findById(bean.getSlotId());
			bean.setSlot(slotDTO.getSlot());
			bean.setSlotId(slotDTO.getId());
			bean.setStatus("Cancel");
			bean.setReqstatus("Pending");
			if (carpoolUsersService.isLeader(user.getId())) {
				bean.setCarpoolId(carpoolUsersService.getCarpoolFor(user.getId()).getCarpoolId());

			} else {
				// not a leader and not in a carpool, so set to -1 to ignore
				bean.setCarpoolId(-1);
			}
			service.Add(bean);

			if (bean.getCarpoolId() > 0 ){
				String carpoolName = carpoolUsersService.getCarpoolNameFor(user.getId());
				model.addAttribute("success", "Booking successful for carpool: " + carpoolName);
			} else {
				model.addAttribute("success", "Booking successfully");
			}
			return "booking";
		}}catch (RecordNotFoundException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "booking";
		}
	}
	
	
	@GetMapping("/cancelBooking")
	public String cancelBooking(@ModelAttribute("form")BookingForm form, Model model, @RequestParam("id") long id, @RequestParam("slotid") long slotid, HttpSession session) {
		SlotDTO slot = slotDAO.findById(slotid);
		slot.setStatus(true);
		slotDAO.saveAndFlush(slot);

		BookingDTO booking = bookingDAO.findById(id);
		booking.setStatus("Canceled");
		bookingDAO.saveAndFlush(booking);
		
		List<BookingDTO> list = null;
		UserDTO user = (UserDTO) session.getAttribute("user");
		String email  = user.getEmail();
		System.out.println("Booking list email: "+email);
		if(user.getUserRole().equals("Admin")) {
			 list = service.list();
		}else {
			list = service.findBookingByEmail(email);
		}
		model.addAttribute("list", list);
		return "bookinglist";
	}
	
	@GetMapping("/approveReq")
	public String approveReq(Model model, @RequestParam("id") long id, HttpSession session) {
	
		BookingDTO dto = service.findBookingById(id);
		dto.setReqstatus("Approved");
		service.update(dto);	
		return "redirect:/bookinglist";
	}
	

	@GetMapping("/declineReq")
	public String declineReq(Model model, @RequestParam("id") long id, HttpSession session) {
	
		BookingDTO dto = service.findBookingById(id);
		dto.setReqstatus("Declined");
		service.update(dto);	
		return "redirect:/bookinglist";
	}
	
}
