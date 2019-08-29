package com.equipment.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipment.manager.payload.ParameterTitleResponse;
import com.equipment.manager.service.ParameterTitleService;

@RestController
@RequestMapping("/api/parameters/titles")
public class ParameterTitleController {

	@Autowired
	private ParameterTitleService parameterTitleService;
	
	@GetMapping
	public List<ParameterTitleResponse> getParameters() {
		return parameterTitleService.getAllParameterTitles();
	}
	
}
