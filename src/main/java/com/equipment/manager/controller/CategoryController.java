package com.equipment.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipment.manager.model.Category;
import com.equipment.manager.payload.CategoryResponse;
import com.equipment.manager.service.CategorySercvice;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategorySercvice categorySercvice;
	
	@GetMapping
	public List<CategoryResponse> getCategories(){
		return categorySercvice.getAllCategories();
	}

}
