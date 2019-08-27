	package com.equipment.manager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.equipment.manager.exception.ResourceNotFoundException;
import com.equipment.manager.model.Category;
import com.equipment.manager.model.Equipment;
import com.equipment.manager.payload.ApiResponse;
import com.equipment.manager.payload.EquipmentRequest;
import com.equipment.manager.payload.EquipmentResponse;
import com.equipment.manager.repository.CategoryRepository;
import com.equipment.manager.repository.CommentRepository;
import com.equipment.manager.repository.EquipmentRepository;

import net.bytebuddy.asm.Advice.Thrown;

@Service
public class EquipmentService {
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

	public List<EquipmentResponse> getAllEquipments() {		
		List<Equipment> equipments = equipmentRepository.findAll();
		List<EquipmentResponse> equipmentResponses = equipments
				.stream()
				.map(equipment -> {
					return new EquipmentResponse(
							equipment.getId(), 
							equipment.getName(), 
							equipment.getDescription(), 
							equipment.getIsValid(),
							equipment.getImageUrl());
				}).collect(Collectors.toList());
		
		return equipmentResponses;
	}
	
	public EquipmentResponse getEquipmentById(Long equipmentId) {
		Equipment equipment = equipmentRepository
					.findById(equipmentId)
					.orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", equipmentId));
		
		return new EquipmentResponse(
				equipment.getId(), 
				equipment.getName(), 
				equipment.getDescription(), 
				equipment.getIsValid(), 
				equipment.getImageUrl());
	}
	
	public Equipment createEquipment(EquipmentRequest equipmentRequest) {
		Equipment equipment = new Equipment();
		Category category = categoryRepository
					.findById(equipmentRequest.getCategoryId())
					.orElseThrow(() -> new ResourceNotFoundException("Category", "id", equipmentRequest.getCategoryId()));
		
		equipment.setName(equipmentRequest.getName());
		equipment.setDescription(equipmentRequest.getDescription());
		equipment.setIsValid(equipmentRequest.getIsValid());
		equipment.setImageUrl(equipmentRequest.getImageUrl());
		equipment.setCategory(category);
		
		return equipmentRepository.save(equipment);
	}
	
//	public Integer updateEquipment(EquipmentRequest equipmentRequest, Long equipmentId) {
//		if (!equipmentRepository.findById(equipmentId).isPresent()) {
//			logger.error("Equipment with Id " + equipmentId + "do not exist");
//			throw new ResourceNotFoundException("Equipment", "id", equipmentId);
//		}
//		
//		return equipmentRepository.update(
//				equipmentId, 
//				equipmentRequest.getName(),
//				equipmentRequest.getDescription(), 
//				equipmentRequest.getIsValid(), 
//				equipmentRequest.getImageUrl(), 
//				equipmentRequest.getCategoryId());
//		);
//				
//		return equipmentRepository.updateEquipment(
//				equipmentId, 
//				equipmentRequest.getName(),
//				equipmentRequest.getDescription(), 
//				equipmentRequest.getIsValid(), 
//				equipmentRequest.getImageUrl(), 
//				equipmentRequest.getCategoryId());
//	}

	
	public ApiResponse deleteEquipment(Long equipmentId) {
		if (!equipmentRepository.findById(equipmentId).isPresent()) {
			logger.error("Equipment with Id " + equipmentId + "do not exist");
			throw new ResourceNotFoundException("Equipment", "id", equipmentId);
		}
		
		equipmentRepository.deleteById(equipmentId);
		
		return new ApiResponse(true, "Equipment Deleted Successfully");
	}
	
	
}
