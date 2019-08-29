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
import com.equipment.manager.payload.EquipmentRequest;
import com.equipment.manager.payload.ParameterRequest;
import com.google.gson.Gson;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EquipmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser("adam")
	public void getAllEquipmentsTest() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/equipments")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[0].name").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[1].name").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[2].name").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[3].name").exists())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[4].name").exists());
	
	}

	@Test 
	@WithMockUser("adam")
	public void getEquipmentByIdTest() throws Exception {
	
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/equipments/{equipmentId}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.name").value("Thin Ultra"));
	
	}
	
	@Test 
	@WithMockUser("adam")
	public void getEquipmentByInvalidIdTest() throws Exception {
	
		mockMvc
			.perform(MockMvcRequestBuilders
			.get("/api/equipments/{equipmentId}", 999999)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());

	}
	
	@Test
	@WithMockUser("adam")
	public void createEquipmentWithParametersToAttach() throws Exception {
		
		Gson gson = new Gson();
		
		ParameterRequest parameterRequestRamMemory = new ParameterRequest((long) 2, "4GB RAM");
		ParameterRequest parameterRequestResolution = new ParameterRequest((long) 6, "1920x1080");
		ParameterRequest parameterRequestBattery = new ParameterRequest((long) 10, "4000 mAh");
		
		Set<ParameterRequest> parameters = new HashSet<>();

		parameters.add(parameterRequestBattery);
		parameters.add(parameterRequestResolution);
		parameters.add(parameterRequestRamMemory);
		
		EquipmentRequest equipmentRequest = new EquipmentRequest(
			"Nokia",
			"The best phone",
			true,
			"nokia.jpg",
			(long) 2,
			parameters
		);
		
		String jsonEquipment = gson.toJson(equipmentRequest);
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.post("/api/equipments/new")
			.content(jsonEquipment)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.message").value("New Equipment added successfully"));
		
	}
	
	@Test
	@WithMockUser("adam")
	public void updateEquipmentWithParametersToAttach() throws Exception {

		Gson gson = new Gson();
		
		ParameterRequest parameterRequestBuildMemory = new ParameterRequest((long) 3, "512 GB");
		ParameterRequest parameterRequestScreenDiagonal = new ParameterRequest((long) 5, "60 cals");
		ParameterRequest parameterRequestBattery = new ParameterRequest((long) 10, "5500 mAh");
		
		Set<ParameterRequest> parameters = new HashSet<>();
		
		parameters.add(parameterRequestBattery);
		parameters.add(parameterRequestScreenDiagonal);
		parameters.add(parameterRequestBuildMemory);
		
		EquipmentRequest equipmentRequest = new EquipmentRequest(
				"Dell",
				"The best Laptop",
				null,
				null,
				(long) 1,
				parameters
		);

		String jsonEquipment = gson.toJson(equipmentRequest);

		mockMvc
			.perform(MockMvcRequestBuilders
			.put("/api/equipments/{equipmentId}", 5)
			.content(jsonEquipment)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.message").value("Equipment updated successfully"));

	}
	
	
	@Test 
	@WithMockUser("adam")
	public void deleteEquipmentByIdTest() throws Exception {

		mockMvc
			.perform(MockMvcRequestBuilders
			.delete("/api/equipments/{equipmentId}", 5)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.success").value(true))
			.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.message").value("Equipment Deleted Successfully"));
		
	}

	@Test
	@WithMockUser("adam")
	public void deleteEquipmentByInvalidIdTest() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders
			.delete("/api/equipments/{equipmentId}", 999999)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
		
	}
	
	@Test
	@WithMockUser("adam")
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

	@Test
	@WithMockUser("adam")
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
