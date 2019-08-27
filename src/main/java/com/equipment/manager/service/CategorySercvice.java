package com.equipment.manager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipment.manager.model.Category;
import com.equipment.manager.payload.CategoryResponse;
import com.equipment.manager.repository.CategoryRepository;

@Service
public class CategorySercvice {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryResponse> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		
		List<CategoryResponse> categoryResponses = categories
				.stream()
				.map(category -> {
					return new CategoryResponse(
							category.getId(), 
							category.getName());
		}).collect(Collectors.toList());
		
		return categoryResponses;
	}
	
}
