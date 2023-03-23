package com.petesparkingmgt.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.petesparkingmgt.dto.UserDTO;

import java.time.LocalDate;
import java.util.List;

public interface UserDAO extends JpaRepository<UserDTO, Long> {
	
	public UserDTO findByEmailAndPassword(String email, String password);
	public UserDTO findByEmail(String email);
	public UserDTO findById(long id);
	public List<UserDTO> findAll();

	public List<UserDTO> getAllByUserRole(String userRole);










}
