package com.petesparkingmgt.service;


import java.util.Date;
import java.util.List;

import com.petesparkingmgt.dao.CarpoolDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.exception.RecordNotFoundException;


@Service
public class UserService {
	
	@Autowired
	public UserDAO dao;

	@Autowired
	public CarpoolUsersService carpoolUsersService;


	//Add the CarpoolDAO object

	public CarpoolDAO carpoolDAO;

	
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
			existingUser.setPoints(user.getPoints());
			existingUser.setLevel(user.getLevel());

			// Save the updated user information to the database
			dao.save(existingUser);
		}
	}

	public void giveAdminPermission(long userid){
		UserDTO existingUser = dao.findById(userid);
		if (existingUser != null) {
			existingUser.setUserRole("Admin");

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


		if (carpoolUsersService.getCarpoolFor(userId) != null){
			carpoolUsersService.leaveCarpoolFor(userId, carpoolUsersService.getCarpoolFor(userId).getCarpoolId());
		}
		// delete from
		dao.deleteById(userId);


	}


}

