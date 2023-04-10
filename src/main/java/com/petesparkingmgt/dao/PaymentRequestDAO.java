package com.petesparkingmgt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petesparkingmgt.dto.PaymentDTO;
import com.petesparkingmgt.dto.PaymentRequestDTO;

public interface PaymentRequestDAO extends JpaRepository<PaymentRequestDTO, Long>{

	public PaymentRequestDTO findByUserID(long id);
	public PaymentRequestDTO findByUserIDAndStatus(long id, String status);

} 
