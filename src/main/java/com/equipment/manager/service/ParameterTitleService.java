package com.equipment.manager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipment.manager.model.ParameterTitle;
import com.equipment.manager.payload.ParameterTitleResponse;
import com.equipment.manager.repository.ParameterTitleRepository;

@Service
public class ParameterTitleService {

	@Autowired
	private ParameterTitleRepository parameterTitleRepository;
		
	public List<ParameterTitleResponse> getAllParameterTitles() {
		List<ParameterTitle> parameterTitles = parameterTitleRepository.findAll();	
		List<ParameterTitleResponse> parameterTitleResponses = parameterTitles
				.stream()
				.map(parameterTitle -> {
					return new ParameterTitleResponse(
							parameterTitle.getId(),
							parameterTitle.getTitle());
				}).collect(Collectors.toList());

		return parameterTitleResponses;
	}
}
