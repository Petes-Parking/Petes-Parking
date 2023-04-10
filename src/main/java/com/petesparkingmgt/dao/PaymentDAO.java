package com.petesparkingmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petesparkingmgt.dto.PaymentDTO;



public interface PaymentDAO extends JpaRepository<PaymentDTO, Long> {
	
	public PaymentDTO findById(long id);
	public List<PaymentDTO> findByEmail(String email);

}
