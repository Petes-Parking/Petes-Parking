package com.petesparkingmgt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.user.UserDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserRepositoryLayerTests {
	@Autowired
	private UserDAO userdao;
	
	//JUnit test for save using
	
	@Test
	@Order(1)
	@Rollback(value = false)
	void Should_Save_User_Test() {
		
		//Arrange
		UserDTO user = new UserDTO();
		user.setId(100);
		user.setFirstName("dummy");
		user.setLastName("Testing");
		user.setDob("12/03/1999");
		user.setGender("Male");
		user.setEmail("dummy@gmail.com");
		user.setPassword("A123456789");
		user.setPhoneNumber("2267889091");
		user.setUserRole("Student");
		
		//act
		userdao.save(user);
		//System.out.println("user id: "+ user.getId());
		
		//assertNotNull(userdao.findById(1L).getId());
		
		//assert
		Assertions.assertThat(user.getId()).isGreaterThan(0);
		
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	void should_Get_User_List_Test() {
		
		List<UserDTO> user = userdao.findAll();
		
		assertThat(user.size()).isGreaterThan(0);
				
	}
	
//	@Test
//	@Order(3)
//	@Rollback(value = false)
//	void should_Get_Single_User_Test() {
//
//		UserDTO user = userdao.findById(72);
//
//		UserDTO user1 = userdao.findByEmail("dummy@gmail.com");
//
//
//		assertEquals("dummy", user1.getFirstName());
//
//	}
//
//
//	@Test
//	@Order(4)
//	@Rollback(value = false)
//	void should_Update_User_Test() {
//
//		UserDTO user = userdao.findById(72);
//		user.setFirstName("Updated_dummy");
//		userdao.save(user);
//
//		assertNotEquals("dummy", userdao.findById(72).getFirstName());
//
//		UserDTO user1 = userdao.findById(100);
//		user1.setFirstName("Updated_dummy");
//		userdao.save(user1);
//
//		assertNotEquals("dummy", userdao.findById(100).getFirstName());
//
//
//
//	}
//
//	@Test
//	@Order(5)
//	@Rollback(value = false)
//	void should_Delete_User_Test() {
//
//		UserDTO user = userdao.findById(72);
//		userdao.delete(user);
//
//		assertThat(userdao.existsById(72L)).isFalse();
//
//		UserDTO user1 = userdao.findById(100);
//		userdao.delete(user1);
//
//		assertThat(userdao.existsById(100)).isFalse();
//
//
//		//userdao.deleteById(65L);
//
////		UserDTO user1 = null;
////
////		UserDTO optional_user = userdao.findByEmail("ob1@gmail.com");
////
//////		if(optional_user.isPresent()) {
//////			user1 = optional_user.get();
//////		}
////
////
////		// assert that the user now null;
////			Assertions.assertThat(user1).isNull();
//
//
//	}
	
	
}
