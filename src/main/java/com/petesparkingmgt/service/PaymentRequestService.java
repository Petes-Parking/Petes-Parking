package com.petesparkingmgt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petesparkingmgt.dao.PaymentRequestDAO;
import com.petesparkingmgt.dto.parking.*;
import com.petesparkingmgt.dto.PaymentRequestDTO;
import com.petesparkingmgt.exception.RecordNotFoundException;
import com.petesparkingmgt.utility.DataUtility;

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
		System.out.println("User ID: " + userId);
		PaymentRequestDTO dto = dao.findByUserID(userId);	 // THIS IS NULL
		dto.setStatus("paid");
		dao.saveAndFlush(dto);
		
		return 	dto;
	}
	
	public PaymentRequestDTO updateExisting(PaymentRequestDTO dto) {
		PaymentRequestDTO prDto = dao.findByUserID(dto.getUserID());
		prDto.setStatus("unpaid");
		prDto.setAmount(dto.getAmount());
		dao.saveAndFlush(prDto);
		
		return 	dto;
	}
	
	public PaymentRequestDTO paymentRequestDTOByUserID(long userId) {
		return 	dao.findByUserID(userId);
		}
	
	
	
}
