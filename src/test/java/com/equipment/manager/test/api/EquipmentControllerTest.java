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

import com.equipment.manager.payload.CommentRequest;
import com.google.gson.Gson;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EquipmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser("adam")
	@Test
	public void getAllEquipmentsTest() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/equipments")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[0].name").value("Thin Ultra"))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[1].name").value("Smartphone"))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[2].name").value("PC"))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[3].name").value("JBL"))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[4].name").value("Lenovo Thinkpad"));
	
	}

	@WithMockUser("adam")
	@Test 
	public void getEquipmentByIdTest() throws Exception {
	
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/equipments/{equipmentId}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.name").value("Thin Ultra"));
	
	}
	
	@WithMockUser("adam")
	@Test 
	public void getEquipmentByInvalidIdTest() throws Exception {
	
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/equipments/{equipmentId}", 999999)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());

	}
	
	@WithMockUser("adam")
	@Test 
	public void deleteEquipmentByIdTest() throws Exception {

		mockMvc
			.perform(MockMvcRequestBuilders
			.delete("/api/equipments/{equipmentId}", 5)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.message").value("Equipment Deleted Successfully"));
		
	}

	@WithMockUser("adam")
	@Test
	public void deleteEquipmentByInvalidIdTest() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.delete("/api/equipments/{equipmentId}", 999999)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		
	}
	
	@WithMockUser("adam")
	@Test
	public void createCommentAssignedToEquipmentTest() throws Exception {
		
		Gson gson = new Gson();
		CommentRequest commentRequest = new CommentRequest("Comment test");
		String jsonComment = gson.toJson(commentRequest);
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.post("/api/equipments/{equipmentId}/comments/new", 1)
			.content(jsonComment)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.message").value("New Comment added successfully"));
							
	}

	@WithMockUser("adam")
	@Test
	public void getCommentsAssignedToEquipmentTest() throws Exception {
				
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/equipments/{equipmentId}/comments", 4)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[0].content").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[1].content").exists());
							
	}

	
}
