package com.equipment.manager.test.api;

import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParameterTitleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser("adam")
	@Test
	public void getAllParametersTest() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/parameters/titles")
			.accept(MediaType.APPLICATION_JSON))	
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[0].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[1].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[2].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[3].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[4].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[5].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[6].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[7].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[8].title").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[9].title").exists());

	}
}
