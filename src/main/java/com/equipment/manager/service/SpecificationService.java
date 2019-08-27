package com.equipment.manager.service;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipment.manager.exception.ResourceNotFoundException;
import com.equipment.manager.model.Equipment;
import com.equipment.manager.model.Parameter;
import com.equipment.manager.model.Specification;
import com.equipment.manager.payload.ParameterRequest;
import com.equipment.manager.repository.EquipmentRepository;
import com.equipment.manager.repository.ParameterRepository;
import com.equipment.manager.repository.ParameterTitleRepository;
import com.equipment.manager.repository.SpecificationRepository;

@Service
public class SpecificationService {
	
	@Autowired
	private SpecificationRepository specificationRepository;
		
	@Autowired
	private ParameterRepository parameterRepository;
	
	@Autowired
	private ParameterTitleRepository parameterTitleRepository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	public void updateSpecification(Set<ParameterRequest> parameterRequests, Long equipmentId) {
		
		Equipment equipment = equipmentRepository
						.findById(equipmentId)
						.orElseThrow(() -> new ResourceNotFoundException("Equipment", "id", equipmentId));
		
		Specification specification = equipment.getSpecification();
		
		Set<Parameter> specificationParameters = specification
						.getParameters();
		
		if (specificationParameters != null) {
			Iterator<Parameter> specificationParameter = specificationParameters.iterator();
			while(specificationParameter.hasNext()) {
				specificationParameter.remove();
			}			
		} 
				
		for (ParameterRequest parameterRequest : parameterRequests) {
			Parameter parameterToAttach = new Parameter(
					parameterTitleRepository
						.findById(parameterRequest.getParameterTitleId())
						.orElseThrow(() -> new ResourceNotFoundException("Parameter Title", "id", parameterRequest.getParameterTitleId())), 
					parameterRequest.getDescription());
			specification.addParameter(parameterToAttach);
			parameterRepository.save(parameterToAttach);						
		}
	}
	
}
