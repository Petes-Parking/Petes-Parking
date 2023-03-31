package com.petesparkingmgt;

import com.petesparkingmgt.dao.CarpoolDAO;
import com.petesparkingmgt.dto.carpools.CarpoolDTO;
import com.petesparkingmgt.form.CarpoolForm;
import com.petesparkingmgt.service.CarpoolService;
import com.petesparkingmgt.service.CarpoolUsersService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.service.PendingUserService;
import com.petesparkingmgt.service.UserService;

import javax.servlet.http.HttpSession;


@AutoConfigureMockMvc(addFilters = false)
@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CarpoolCtlTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userservice;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private UserDAO userdao;
	
	@Autowired
	public PendingUserService pendingUserService;


	@MockBean
	private CarpoolService service;

	@MockBean
	private CarpoolDAO dao;

	@MockBean
	private CarpoolUsersService carpoolUsersService;

	@Mock
	private HttpSession session;
	@Autowired
	private WebApplicationContext context;
	
	// TODO: THIS SECTION IS NOT Complete - IN PROGRESS
	
	@Before(value = "")
	public void setUp() {
		mockMvc  = MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	

	


	/**
	 * Create carpool unit test
	 * @throws Exception
	 */

	@SuppressWarnings("unlikely-arg-type")

	@Test
	public void testCreateCarpool() throws Exception {
		// Arrange
		UserDTO user = new UserDTO();
		user.setId(1);
		user.setFirstName("John");
		user.setLastName("Doe");

		CarpoolDTO carpoolDTO = new CarpoolDTO();
		carpoolDTO.setCarPoolName("Unit Test");
		carpoolDTO.setLeaderId(user.getId());

		CarpoolForm form = new CarpoolForm();
		form.setDTO(carpoolDTO);

//		when(session.getAttribute("user")).thenReturn(user);
		when(service.isNameTaken(carpoolDTO.getCarPoolName())).thenReturn(false);
		when(dao.getCarpoolDTOByLeaderId(user.getId())).thenReturn(carpoolDTO);

		// Act and Assert
		mockMvc.perform(post("/createCarpool")
						.sessionAttr("user", user)
						.flashAttr("carform", form))
				.andExpect(status().isOk())
				.andExpect(view().name("carpool"))
				.andExpect(model().attribute("carPoolName", carpoolDTO.getCarPoolName()))
				.andExpect(model().attribute("hasCarpool", true))
				.andExpect(model().attribute("isLeader", true));

		verify(service, times(1)).isNameTaken(eq("Unit Test"));
		verify(service, times(1)).add(argThat(carpoolDTO1 -> carpoolDTO1.getCarPoolName().equals("Unit Test") && carpoolDTO.getLeaderId() == 1));

	}




	

	

}
