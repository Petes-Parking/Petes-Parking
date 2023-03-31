package com.petesparkingmgt.ctl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import com.petesparkingmgt.dao.FavoriteDAO;
import com.petesparkingmgt.dao.HistoryDAO;
import com.petesparkingmgt.dto.*;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import com.petesparkingmgt.form.FavoriteForm;
import com.petesparkingmgt.points.PointsManager;
import com.petesparkingmgt.service.*;
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


@Controller
public class BookingCtl {

     @Autowired
     public SlotDAO slotDAO;
     
     @Autowired
     public BookingDAO bookingDAO;
     
     @Autowired
     public BookingService service;


	@Autowired
	public UserService userService;

	 @Autowired
	 public HistoryService historyService;
     
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
			int points = historyService.getPointsFor(user.getId());
			int priorLevel = user.getLevel();
			int totalPoints = points + user.getPoints();

			user.setPoints(totalPoints);
			PointsManager.LevelWrapper wrapper = PointsManager.getLevel(user.getPoints());


			user.setLevel(wrapper.getLevel());

			userService.update(user);




			if (bean.getCarpoolId() > 0 ){
				String carpoolName = carpoolUsersService.getCarpoolNameFor(user.getId());
				List<UserDTO> members = carpoolService.getUserDTOSForCarpool(bean.getCarpoolId());

				// set points for every member in the carpool
				for (UserDTO member : members) {
					if (member.getId() == user.getId()) continue;

					int addedPoints = historyService.getPointsFor(member.getId());
					System.out.println("Adding " + addedPoints + " to " + member.getPoints() + " for " + member.getEmail());
					member.setPoints(member.getPoints() + addedPoints);
					PointsManager.LevelWrapper memWrapper = PointsManager.getLevel(user.getPoints());

					member.setLevel(memWrapper.getLevel());
					userService.update(member);

				}

				model.addAttribute("success", "Booking successful for carpool: " + carpoolName);
				model.addAttribute("success2", "Gained: " + points + " points. You now have " + totalPoints + " total points!");
				model.addAttribute("success3", "Level: " + wrapper.getLevel() + ". Till next level: " + wrapper.getNextLevelThreshold());

			} else {
				model.addAttribute("success", "Booking successfully!");
				model.addAttribute("success2", "Gained: " + points + " points. You now have " + totalPoints + " total points!");
				model.addAttribute("success3", "Level: " + wrapper.getLevel() + ". Till next level: " + wrapper.getNextLevelThreshold());

			}
			// Second conditional is so it does not collide during demo purposes
			if (priorLevel != wrapper.getLevel() && !(bean.getCarpoolId() > 0)) {
				model.addAttribute("levelUp", true);
				model.addAttribute("level", wrapper.getLevel());
				model.addAttribute("pointsToNext", wrapper.getNextLevelThreshold());
				return "levelup";
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
<<<<<<< HEAD
	
	
	@GetMapping("/userCancel")
	public String cancel(Model model, @RequestParam("id") long id, HttpSession session) {
	
		BookingDTO dto = service.findBookingById(id);
		dto.setReqstatus("Cancelled");
		service.update(dto);	
		return "redirect:/bookinglist";
	}
	
=======
>>>>>>> 2755eb0fa6ac37f74955151034915d1acc868ab0
}
