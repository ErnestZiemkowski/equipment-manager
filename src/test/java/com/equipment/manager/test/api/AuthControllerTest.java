package com.equipment.manager.test.api;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.equipment.manager.model.User;
import com.equipment.manager.payload.LoginRequest;
import com.equipment.manager.payload.SignUpRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest  {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void signInValidUserTest() throws Exception {
		
		Gson gson = new Gson();
		LoginRequest loginRequest = new LoginRequest("adam", "adam");
		String jsonUser = gson.toJson(loginRequest);
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.post("/api/auth/signin")
			.content(jsonUser)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").isNotEmpty())
			.andExpect(MockMvcResultMatchers.jsonPath("$.tokenType").isNotEmpty());
	
	}
	
	@Test
	public void signInAuthorizedUserInvalidPasswordTest() throws Exception {
		
		Gson gson = new Gson();
		LoginRequest loginRequest = new LoginRequest("adam", "badpassword");
		String jsonUser = gson.toJson(loginRequest);
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.post("/api/auth/signin")
			.content(jsonUser)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized());

	}
	
	@Test
	public void signInUnauthenticatedUserInvalidPasswordTest() throws Exception {
		
		Gson gson = new Gson();
		LoginRequest loginRequest = new LoginRequest("nonexistinguser", "badpassword");
		String jsonUser = gson.toJson(loginRequest);
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.post("/api/auth/signin")
			.content(jsonUser)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized());

	}	
	
	@Test 
	public void signUpNonExistingUser() throws Exception {
		
		Gson gson = new Gson();
		SignUpRequest registerRequest = new SignUpRequest("nonexistinguser", "nonexistinguser@manager.com", "pass");
		String jsonUser = gson.toJson(registerRequest);

		mockMvc
			.perform(MockMvcRequestBuilders
			.post("/api/auth/signup")
			.content(jsonUser)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User registered successfully"));

	}

	@Test 
	public void signUpExistingUser() throws Exception {
		
		Gson gson = new Gson();
		SignUpRequest registerRequest = new SignUpRequest("adam", "adam@manager.com", "adam");
		String jsonUser = gson.toJson(registerRequest);

		mockMvc
			.perform(MockMvcRequestBuilders
			.post("/api/auth/signup")
			.content(jsonUser)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest())
			.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Username is already taken!"));

	}

}
