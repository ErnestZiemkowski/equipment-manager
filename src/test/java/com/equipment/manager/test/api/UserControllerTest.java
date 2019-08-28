package com.equipment.manager.test.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void checkUsernameAvailabilityTest() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/user/checkUsernameAvailability?username=adam")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.available").value(false));

	}

	@Test
	public void checkEmailAvailabilityTest() throws Exception {

		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/user/checkEmailAvailability?email=user15231235545646")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.available").value(true));
		
	}
}
