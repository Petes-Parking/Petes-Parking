package com.petesparkingmgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petesparkingmgt.dao.PaymentRequestDAO;
import com.petesparkingmgt.dto.PaymentRequestDTO;

@Service
public class PaymentRequestService {

	@Autowired
	public PaymentRequestDAO dao;
	
	public void Add(PaymentRequestDTO dto) {
	   dao.save(dto);
 
	}
	
	public PaymentRequestDTO DuePaymentByUser(long userId, String status) {
	return 	dao.findByUserIDAndStatus(userId, status);
	}
	
	public PaymentRequestDTO update(long userId) {
		
		PaymentRequestDTO dto = dao.findByUserID(userId);
		
		dto.setStatus("paid");
		dao.saveAndFlush(dto);
		return 	dto;
		
	
	}
	
	public PaymentRequestDTO paymentRequestDTOByUserID(long userId) {
		return 	dao.findByUserID(userId);
		}
	
	
	
}
