package com.equipment.manager.test.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser("adam")
	@Test
	public void getAllCategoriesWithAuthorizedUserTest() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/categories")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[0].name").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[1].name").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[2].name").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[3].name").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[4].name").exists());

	}
	
	@Test
	public void getAllCategoriesWithUnauthorizedUserTest() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/categories")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized());
	}
	
}
