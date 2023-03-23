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
	
	public List<UserDTO> list() {
		return dao.findAll();
	}
	
	public void update(UserDTO user){
		UserDTO existingUser = dao.findById(user.getId());
		if (existingUser != null) {
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setEmail(user.getEmail());
			existingUser.setPassword(user.getPassword());
			existingUser.setGender(user.getGender());

			// Save the updated user information to the database
			dao.save(existingUser);
		}
	}
	
	public UserDTO findUserById(long id) {
		return dao.findById(id);
	}
	
	


	public void delete(long id) throws Exception {
		if(id>0)
		{
			dao.deleteById(id);
		}else {
			throw new Exception("Not a valid id");
		}	
	}


	public List<UserDTO> getAllUser() {
		return dao.findAll();
	}


	public void deleteUser(Long userId) {
		// need to do more here, check other tables and delete but for now keep it simple
		dao.deleteById(userId);

	}
}

