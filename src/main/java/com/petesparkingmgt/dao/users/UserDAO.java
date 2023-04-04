package com.petesparkingmgt.dao.users;



import org.springframework.data.jpa.repository.JpaRepository;

import com.petesparkingmgt.dto.user.UserDTO;

import java.util.List;

public interface UserDAO extends JpaRepository<UserDTO, Long> {
	
	public UserDTO findByEmailAndPassword(String email, String password);
	public UserDTO findByEmail(String email);
	public UserDTO findById(long id);
	public List<UserDTO> findAll();
	
	//Optional<UserDTO> OptionalfindUserByEmail(String email);
	

	public List<UserDTO> getAllByUserRole(String userRole);
	public boolean existsById(long i);

	public boolean existsByEmail(String email);











}
