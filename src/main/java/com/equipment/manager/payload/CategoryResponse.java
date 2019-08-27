package com.equipment.manager.payload;

import javax.validation.constraints.NotBlank;

public class CategoryResponse {
	
	@NotBlank
	private Long id;

	@NotBlank
	private String name;

	public CategoryResponse(@NotBlank Long id, @NotBlank String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
