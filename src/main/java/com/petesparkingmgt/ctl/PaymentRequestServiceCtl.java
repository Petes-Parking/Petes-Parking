package com.petesparkingmgt.ctl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.petesparkingmgt.dto.PaymentDTO;
import com.petesparkingmgt.dto.PaymentRequestDTO;
import com.petesparkingmgt.form.BookingForm;
import com.petesparkingmgt.form.PaymentForm;
import com.petesparkingmgt.form.PaymentRequestForm;
import com.petesparkingmgt.service.BookingService;
import com.petesparkingmgt.service.PaymentRequestService;
import com.petesparkingmgt.utility.DataUtility;
import com.petesparkingmgt.dto.user.*;

@Controller
public class PaymentRequestServiceCtl {

	@Autowired
	public PaymentRequestService service;
	
	@Autowired
	public BookingService bookingService ;
	
	@Autowired
	public PaymentRequestService paymentRequestService ;
	
	@PostMapping("/paymentRequest")
	public String add(@ModelAttribute("form")PaymentRequestForm form, BindingResult bindingResult, Model model, HttpSession session ) {
         	
		PaymentRequestDTO dto = form.getDTO();	
		
		long userId = dto.getUserID();
		PaymentRequestDTO  paymentRequestDTO =  paymentRequestService.paymentRequestDTOByUserID(userId);
		
		if(paymentRequestDTO == null)
		{
			dto.setStatus("unpaid");
			service.Add(dto);
			model.addAttribute("success", "Request Sent");
		}else if(paymentRequestDTO.getStatus().equals("paid")) {
			
			System.out.println("Second if is runnig now......dto: "+dto);
			service.updateExisting(dto);
			model.addAttribute("success", "Request Sent");
		}
		
		else {
			model.addAttribute("error", "Request alreay Sent");
		}
		
		
		return "bookinglist";
	}
	
	

	
	
}
