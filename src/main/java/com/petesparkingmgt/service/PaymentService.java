package com.petesparkingmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petesparkingmgt.dao.PaymentDAO;
import com.petesparkingmgt.dto.PaymentDTO;


@Service
public class PaymentService {
	
	@Autowired
	public PaymentDAO dao;
	
	public PaymentDTO Add(PaymentDTO dto) {
		PaymentDTO  payment = dao.save(dto);
       return  payment;
	}
	

	public List<PaymentDTO> list(){
		List<PaymentDTO> dto = dao.findAll();
		return dto;
	}
	
	public List<PaymentDTO> list(String email){
		List<PaymentDTO> dto = dao.findByEmail(email);
		return dto;
	}
	


}
