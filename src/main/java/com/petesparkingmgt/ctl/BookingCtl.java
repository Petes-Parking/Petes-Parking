package com.petesparkingmgt.ctl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import com.petesparkingmgt.dao.EmailPreferencesDAO;
import com.petesparkingmgt.dao.parking.BookingDAO;
import com.petesparkingmgt.dao.parking.SlotDAO;
import com.petesparkingmgt.dao.users.HistoryDAO;
import com.petesparkingmgt.dao.users.VehicleDAO;
import com.petesparkingmgt.dto.*;
import com.petesparkingmgt.dto.carpools.CarpoolUserDTO;
import com.petesparkingmgt.dto.parking.ParkingDTO;
import com.petesparkingmgt.dto.parking.SlotDTO;
import com.petesparkingmgt.dto.user.EmailPreferencesDTO;
import com.petesparkingmgt.dto.user.HistoryDTO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.dto.user.VehicleDTO;
import com.petesparkingmgt.form.BookingPaymentForm;
import com.petesparkingmgt.form.FavoriteForm;
import com.petesparkingmgt.form.PaymentRequestForm;
import com.petesparkingmgt.points.PointsManager;
import com.petesparkingmgt.service.*;
import com.petesparkingmgt.utility.DataUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.petesparkingmgt.exception.RecordNotFoundException;
import com.petesparkingmgt.form.BookingForm;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class BookingCtl {

     @Autowired
     public SlotDAO slotDAO;
     
     @Autowired
     public BookingDAO bookingDAO;
     
     @Autowired
     public BookingService service;

	@Autowired
	public VehicleDAO vehicleDAO;
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
	@Autowired
	public EmailPreferencesDAO emailDAO;
	
	@Autowired
    public PaymentRequestService paymentRequestService;




	
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

		UserDTO user = (UserDTO) session.getAttribute("user");

		System.out.println("form: " + form);
		try {
			if (bindingResult.hasErrors()) {
				System.out.println("bindingResult : " + bindingResult);
				return "booking";
			} else {


				if (carpoolUsersService.isInCarpoolButNotLeader(user.getId())) {
					// do not allow them to book, only the leader can book
					model.addAttribute("error", "Only the carpool leader can reserve a slot!");
					return "booking";
				} else if (carpoolUsersService.isLeader(user.getId()) && carpoolService.hasReservation(carpoolUsersService.getCarpoolFor(user.getId()).getCarpoolId())) {
					// they are a leader but there is currently a reservation, can only have one at a time
					model.addAttribute("error", "You can only book one reservation per carpool at a time!");
					return "booking";
				}
				BookingDTO bean = form.getDTO();

				if (!service.isBookingDurationValid(bean)){
					model.addAttribute("error", "The maximum duration is 10 hours! Please choose a shorter duration.");
					return "booking";
				}

				HistoryDTO historyBean = new HistoryDTO();
				int year1 = bean.getFromBookingDate().getYear() + 1900;
				int year2 = bean.getToBookingDate().getYear() + 1900;
				int month1 = bean.getFromBookingDate().getMonth() + 1;
				int month2 = bean.getToBookingDate().getMonth() + 1;
				int day1 = bean.getFromBookingDate().getDay();
				int day2 = bean.getToBookingDate().getDay();
				boolean isDate1Weekend = (day1 == 6 || day1 == 0);
				boolean isDate2Weekend = (day2 == 6 || day2 == 0);
				boolean canPark = false;
				String permitNeeded = "";
				String displayMessage = "";
				if (isDate1Weekend && isDate2Weekend && year1 == year2 && month1 == month2) {
					canPark = true;
				} else {
					String area = bean.getParkingName();
					vehicleDAO.getVehicleDTOByUserId(user.getId());
					boolean needA = parkingService.findParkingByName(area).getPermitA().equals("on");
					boolean needB = parkingService.findParkingByName(area).getPermitB().equals("on");
					boolean needC = parkingService.findParkingByName(area).getPermitC().equals("on");
					boolean needCG = parkingService.findParkingByName(area).getPermitCG().equals("on");
					boolean needR = parkingService.findParkingByName(area).getPermitR().equals("on");
					boolean needD = parkingService.findParkingByName(area).getPermitD().equals("on");
					boolean hasA = !(vehicleDAO.getVehicleDTOByUserId(user.getId()).getPermitA() == null);
					boolean hasB = !(vehicleDAO.getVehicleDTOByUserId(user.getId()).getPermitB() == null);
					boolean hasC = !(vehicleDAO.getVehicleDTOByUserId(user.getId()).getPermitC() == null);
					boolean hasCG = !(vehicleDAO.getVehicleDTOByUserId(user.getId()).getPermitCG() == null);
					boolean hasR = !(vehicleDAO.getVehicleDTOByUserId(user.getId()).getPermitR() == null);
					boolean hasD = !(vehicleDAO.getVehicleDTOByUserId(user.getId()).getPermitD() == null);
					if ((needA && hasA) || (needB && hasB) || (needC && hasC) || (needCG && hasCG) || (needR && hasR) || (needD && hasD)) {
						canPark = true;
					} else if (!needA && !needB && !needC && !needCG && !needR && !needD) {
						canPark = true;
					}else if (needA) {
						permitNeeded = "A";
						displayMessage = "You do not have an A permit. Please follow this link to purchase one: https://www.purdue.edu/parking/permits/purchase.html";
					} else if (needB) {
						permitNeeded = "B";
						displayMessage = "You do not have a B permit. Please follow this link to purchase one: https://www.purdue.edu/parking/permits/purchase.html";
					} else if (needC) {
						permitNeeded = "C";
						displayMessage = "You do not have a C permit. Please follow this link to purchase one: https://www.purdue.edu/parking/permits/purchase.html";
					} else if (needCG) {
						permitNeeded = "CG";
						displayMessage = "You do not have a C Garage permit. Please follow this link to purchase one: https://www.purdue.edu/parking/permits/purchase.html";
					} else if (needR) {
						permitNeeded = "R";
						displayMessage = "You do not have a Residence Hall permit. Please follow this link to purchase one: https://www.purdue.edu/parking/permits/purchase.html";
					} else {
						permitNeeded = "D";
						displayMessage = "You do not have a Disability permit. Please follow this link to apply for one: http://www.purdue.edu/parking/permits/disabilities/index.html";
					}
				}
				if (!canPark) {
					// PROMPT MESSAGE HERE YOU CANT PARK HERE
					System.out.println("this:     " + displayMessage);
					model.addAttribute("error", displayMessage);
					return "booking";
				} else {
					if (isDateInFuture(bean.getFromBookingDate())) {
						// trying to book in the future, cancel and send payment page
						model.addAttribute("firstName", bean.getFirstName());
						model.addAttribute("lastName", bean.getLastName());
						model.addAttribute("email", bean.getEmail());
						model.addAttribute("phoneNumber", bean.getPhoneNumber());
						model.addAttribute("parkingName", bean.getParkingName());
						model.addAttribute("slotId", bean.getSlotId());
						model.addAttribute("fromBookingDate", form.getFromBookingDate());
						model.addAttribute("toBookingDate", form.getToBookingDate());
						model.addAttribute("fromTime", bean.getFromTime());
						model.addAttribute("toTime", bean.getToTime());

						model.addAttribute("requiredPoints", service.calculatePoints(bean));
						model.addAttribute("userPoints", user.getPoints());
						return "bookingpayment";
					}
					historyBean.setUserId(user.getId());
					historyBean.setArea(form.getParkingName());
					historyBean.setDate(form.getFromBookingDate());
					historyDAO.save(historyBean);

					bean.setId(0);
					SlotDTO slotDTO = slotDAO.findById(bean.getSlotId());
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
					EmailPreferencesDTO emailDTO = emailDAO.getByUserID(user.getId());
					if (emailDTO != null) {
						int emailPref = emailDTO.getExpirationPref();
						System.out.print("Email preference: ");
						System.out.println(emailPref);
						if (emailPref == 1) {
							EmailService emailService = new EmailService();
							System.out.println("Email pref is on");
							Date toDate = bean.getToBookingDate();
							String toTime = bean.getToTime();
							String toDateString = toDate.toString();
							toDateString = toDateString.substring(0, 11) + toTime + toDateString.substring(16, 28);

							Date fromDate = bean.getFromBookingDate();
							String fromTime = bean.getFromTime();
							String fromDateString = fromDate.toString();
							fromDateString = fromDateString.substring(0, 11) + fromTime + fromDateString.substring(16, 28);

							SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
							Date endDate = dateFormat.parse(toDateString);
							Date startDate = dateFormat.parse(fromDateString);
							System.out.println("Date before time subtracted: " + endDate);

							int expTimer = (int) emailDTO.getTimer();

							Calendar calendar = Calendar.getInstance();
							calendar.setTime(endDate);
							calendar.add(Calendar.MINUTE, -expTimer);

							Date newEndDate = calendar.getTime();
							System.out.println("Date after time subtracted: " + newEndDate);

							if (newEndDate.after(startDate)) {
								System.out.println("new end date is after start date, will send email");
								Timer timer = new Timer();

								timer.schedule(new TimerTask() {
									public void run() {
										try {
											emailService.createExpiringTimerEmail(bean, expTimer);
										} catch (NoSuchAlgorithmException e) {
											throw new RuntimeException(e);
										} catch (KeyManagementException e) {
											throw new RuntimeException(e);
										}
									}
								}, newEndDate);
							} else {
								System.out.println("new end date is before start date, don't send email!!!!");
							}
						} else {
							System.out.println("No email sent since pref is off");
						}
					} else {
						System.out.println("email is null!");
					}
					int points = historyService.getPointsFor(user.getId());
					int priorLevel = user.getLevel();
					int totalPoints = points + user.getPoints();

					user.setPoints(totalPoints);
					PointsManager.LevelWrapper wrapper = PointsManager.getLevel(user.getPoints());


					user.setLevel(wrapper.getLevel());

					userService.update(user);
					model.addAttribute("success", "Booking successfully!");
					model.addAttribute("success2", "Gained: " + points + " points. You now have " + totalPoints + " total points!");
					model.addAttribute("success3", "Level: " + wrapper.getLevel() + ". Till next level: " + wrapper.getNextLevelThreshold());

					if (bean.getCarpoolId() > 0) {
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
				}
			}
			} catch(RecordNotFoundException e){
				// TODO: handle exception
				model.addAttribute("error", e.getMessage());
				e.printStackTrace();
				return "booking";
			} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	// NOT DONE YET, MAKE SURE EVERYTHING WORKS LIKE HISTORY & CARPOOLS AND POINTS AND LEVELS
	@PostMapping("/booking/confirmPayment")
	public ModelAndView confirmPayment(@Valid @ModelAttribute("bookingForm") BookingPaymentForm form, BindingResult bindingResult, Model model, RedirectAttributes attributes, HttpSession session) {
		System.out.println(form + " ++ confirmPayment");
		BookingDTO bean = form.getDTO();
		int cost = form.getCost();
		System.out.println(cost + " -- cost " + bean + " -- confirm payment");
		UserDTO user = (UserDTO) session.getAttribute("user");
		user.setPoints(user.getPoints() - cost);

		HistoryDTO historyBean = new HistoryDTO();
		historyBean.setUserId(user.getId());
		historyBean.setArea(bean.getParkingName());
		historyBean.setDate(form.getFromBookingDate());
		historyDAO.save(historyBean);

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


		userService.update(user);


		ModelAndView modelAndView = new ModelAndView("redirect:/bookinglist");


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

			attributes.addFlashAttribute("success", "Booking successful for carpool: " + carpoolName);

		} else {
			attributes.addFlashAttribute("success", "Booking successfully!");
			attributes.addFlashAttribute("success", "You spent  " + cost + " points. You now have " + user.getPoints() + " total points!");

		}


		return modelAndView;
	}

//	@PostMapping("/addBooking")
//	public String Add(@Valid @ModelAttribute("form")BookingForm form,  BindingResult bindingResult, Model model) {
//
//		System.out.println("Parking booking form: "+form);
//		try {
//			if (bindingResult.hasErrors()) {
//				System.out.println("bindingResult : "+bindingResult);
//				return "booking";
//			}else {
//				BookingDTO bean = form.getDTO();
//				bean.setId(0);
//
//				List<String> slotList =  form.getSlotId2();
//
//				for (String id : slotList ) {
//					SlotDTO slotDTO =	slotDAO.findById(DataUtility.getLong(id));
//					bean.setSlot(slotDTO.getSlot());
//					bean.setSlotId(slotDTO.getId());
//					bean.setStatus("Cancel");
//					bean.setReqstatus("Request");
//					bean.setTotalPrice(DataUtility.getLong("50"));
//					service.Add(bean);
//
//				}
//				model.addAttribute("success", "Booking successfully");
//				//model.addAttribute("totalPrice", 50);
//				return "booking";
//			}}catch (RecordNotFoundException e) {
//			// TODO: handle exception
//			model.addAttribute("error", e.getMessage());
//			e.printStackTrace();
//			return "booking";
//		}
//	}

	
	@GetMapping("/bookinglist")
	public String list(@ModelAttribute("form")PaymentRequestForm form, Model model, HttpSession session){
		List<BookingDTO> list = null;
		PaymentRequestDTO paymentRequestDTO = null;
		
		UserDTO user = (UserDTO) session.getAttribute("user");
		String email  = user.getEmail();
		
		paymentRequestDTO    = paymentRequestService.DuePaymentByUser(user.getId(), "unpaid");
		if(user.getUserRole().equals("Admin")) {
			 list = service.list();
		}else {
			list = service.findBookingByEmail(email);
			
		}
	model.addAttribute("list", list);
	model.addAttribute("paymentRequestDTO", paymentRequestDTO);
	
	return "bookinglist";
		
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

	
	
	@GetMapping("/userCancel")
	public String cancel(Model model, @RequestParam("id") long id, HttpSession session) {
	
		BookingDTO dto = service.findBookingById(id);
		dto.setReqstatus("Cancelled");
		service.update(dto);	
		return "redirect:/bookinglist";
	}

	public boolean isDateInFuture(Date givenDate) {
		LocalDate localGivenDate = givenDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate today = LocalDate.now();
		return localGivenDate.isAfter(today);
	}

}
