package com.petesparkingmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petesparkingmgt.dto.BookingDTO;

public interface BookingDAO extends JpaRepository<BookingDTO, Long>{
	
	public List<BookingDTO> findByEmail(String email);
	public BookingDTO findById(long id);

}
