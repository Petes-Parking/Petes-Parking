package com.petesparkingmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petesparkingmgt.dao.PaymentRequestDAO;
import com.petesparkingmgt.dto.PaymentRequestDTO;
import com.petesparkingmgt.dto.parking.*;
import com.petesparkingmgt.dto.user.*;
import com.petesparkingmgt.exception.RecordNotFoundException;

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
	
	public List<PaymentRequestDTO> list() {
		return dao.findAll();
	}
	
	public PaymentRequestDTO findByUserID(long id) {
		return dao.findByUserID(id);
	}

	
	
}
