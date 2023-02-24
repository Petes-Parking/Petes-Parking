package com.petesparkingmgt.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.exception.RecordNotFoundException;


@Service
public class UserService {
	
	@Autowired
	public UserDAO dao;
	
	public void add(UserDTO dto) {		
		dao.save(dto);		
	}
	
	public UserDTO login(String email, String password) {		
		return dao.findByEmailAndPassword(email, password);		
	}

}
