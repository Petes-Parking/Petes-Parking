package com.petesparkingmgt.ctl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.petesparkingmgt.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petesparkingmgt.dto.BookingDTO;
import com.petesparkingmgt.dto.PaymentDTO;
import com.petesparkingmgt.dto.user.*;
import com.petesparkingmgt.form.PaymentForm;
import com.petesparkingmgt.service.BookingService;
import com.petesparkingmgt.service.PaymentRequestService;
import com.petesparkingmgt.service.PaymentService;





@Controller
public class PaymentCtl {
	
	@Autowired
	private PaymentService service;
	
	@Autowired
	private PaymentRequestService paymentRequestService;
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/payment")
	public String payment(@ModelAttribute("form")PaymentForm form, Model model) {
		return "payment";
	}
	
	@PostMapping("/addPayment")
	public String add(@ModelAttribute("form")PaymentForm form, BindingResult bindingResult, Model model, HttpSession session,@RequestParam("id") long id ) throws NoSuchAlgorithmException, KeyManagementException {

		
		if(bindingResult.hasErrors()) {
			return "payment";
		}else {
			PaymentDTO dto = form.getDTO();

			UserDTO user = (UserDTO)session.getAttribute("user");
			System.out.println(user.getEmail());
			dto.setEmail(user.getEmail());
			service.Add(dto);
			paymentRequestService.update(user.getId());
			BookingDTO bookingDTO = bookingService.findBookingById(id);
			bookingDTO.setPaymentStatus("Paid");
			bookingService.update(bookingDTO);
			model.addAttribute("success", "Payment Done!");

			EmailService emailService = new EmailService();
			emailService.createPaymentEmail(dto, user);
		}
		return "payment";
	}
	
	
	@GetMapping("/paymentlist")
	public String list(@ModelAttribute("form")PaymentForm form, Model model, HttpSession session) {
		
		UserDTO user = (UserDTO) session.getAttribute("user");
		List<PaymentDTO> list =null;
		if(user.getUserRole().equals("Student")) {
			list = service.list(user.getEmail());
		}else {
			list = service.list();
		}
		 
        model.addAttribute("list", list);
		return "paymentlist";
	}
	
	
	

}
