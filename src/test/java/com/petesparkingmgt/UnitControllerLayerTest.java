package com.petesparkingmgt;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petesparkingmgt.ctl.UserCtl;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import com.petesparkingmgt.service.PendingUserService;
import com.petesparkingmgt.service.UserService;

import junit.framework.Assert;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;


@AutoConfigureMockMvc(addFilters = false)
@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitControllerLayerTest {
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
	
	@Autowired
	private WebApplicationContext context;
	
	// TODO: THIS SECTION IS NOT IN PROGRESS
	
	@Before(value = "")
	public void setUp() {
		mockMvc  = MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void should_Add_A_User() throws Exception {
	    UserDTO user = new UserDTO();
	    user.setFirstName("newUser");
	    user.setLastName("Testing");
	    user.setDob("12/03/1999");
	    user.setGender("Male");
	    user.setEmail("newTest@gmail.com");
	    user.setPassword("A123456789");
	    user.setPhoneNumber("2267889091");
	    user.setUserRole("Admin");

	    String JsonDataRequest = mapper.writeValueAsString(user);

	    MvcResult result = mockMvc.perform(post("/user/addUser").content(JsonDataRequest)
	            .contentType(MediaType.APPLICATION_JSON_VALUE))
	            .andExpect(status().isOk())
	            .andReturn();

	    // Now we change the result in string format
	    String resultContext = result.getResponse().getContentAsString();

	    // Read the value and convert it to a response
	    Response response = mapper.readValue(resultContext, Response.class);
	    Assertions.assertThat(response.equals(Boolean.TRUE));

	  

	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void should_Aprrove_A_User() throws Exception {
	    UserDTO user = new UserDTO();

	    String JsonDataRequest = mapper.writeValueAsString(user);

	    MvcResult result = mockMvc.perform(post("/user/userApprove")
	            .contentType(MediaType.APPLICATION_JSON_VALUE))
	            .andExpect(status().isOk())
	            .andReturn();

	    // Now we change the result in string format
	    String resultContext = result.getResponse().getContentAsString();

	    // Read the value and convert it to a response
	    Response response = mapper.readValue(resultContext, Response.class);
	    
	    Assertions.assertThat(response.equals("Approved"));

	  

	}
	


	

	

}
