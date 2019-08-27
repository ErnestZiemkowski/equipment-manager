package com.equipment.manager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipment.manager.model.Equipment;
import com.equipment.manager.payload.ApiResponse;
import com.equipment.manager.payload.CommentRequest;
import com.equipment.manager.payload.CommentResponse;
import com.equipment.manager.payload.EquipmentRequest;
import com.equipment.manager.payload.EquipmentResponse;
import com.equipment.manager.security.CurrentUser;
import com.equipment.manager.security.UserPrincipal;
import com.equipment.manager.service.CommentService;
import com.equipment.manager.service.EquipmentService;
import com.equipment.manager.service.SpecificationService;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private SpecificationService specificationService; 
	
	@GetMapping
	public List<EquipmentResponse> getEquipments(){
		return equipmentService.getAllEquipments();
	}
	
	@GetMapping("/{equipmentId}")
	public EquipmentResponse getEquipmentById(@PathVariable Long equipmentId) {
		return equipmentService.getEquipmentById(equipmentId);
	}

	@PostMapping("/new")
	public ApiResponse createEquipment(@Valid @RequestBody EquipmentRequest equipmentRequest) {
		equipmentService.createEquipment(equipmentRequest);
		specificationService.updateSpecification(
				equipmentRequest.getParameters(),
				equipmentRequest.getEquipmentId());
		
		return new ApiResponse(true, "New Equipment added successfully");
	}
	
//	@PutMapping("/{equipmentId}")
//	public ApiResponse updateEquipment(@Valid @RequestBody EquipmentRequest equipmentRequest, @PathVariable Long equipmentId) {
//		int rowsAffected = equipmentService.updateEquipment(equipmentRequest, equipmentId);
//		
//		 
//		return new ApiResponse(true, "Equipment successfully updated");
//	}
	
	@DeleteMapping("/{equipmentId}")
	public ApiResponse deleteEquipmentById(@PathVariable Long equipmentId) {
		return equipmentService.deleteEquipment(equipmentId);
	}	
	
	@PostMapping("/{equipmentId}/comments/new")
	public ApiResponse createComment(@RequestBody CommentRequest commentRequest, @PathVariable Long equipmentId) {
		commentService.createComment(commentRequest, equipmentId);
		
		return new ApiResponse(true, "New Comment added successfully");
	}
	
	@GetMapping("/{equipmentId}/comments")
	public List<CommentResponse> getComments(@PathVariable Long equipmentId) {
		return commentService.getCommentsByEquipmentId(equipmentId);
	}
}

