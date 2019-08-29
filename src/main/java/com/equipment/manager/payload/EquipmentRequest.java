package com.equipment.manager.payload;

import java.util.List;
import java.util.Set;

public class EquipmentRequest {

	private String name;
	
	private String description;
	
	private Boolean isValid;
	
	private String imageUrl;
	
	private Long categoryId;
	
	private Set<ParameterRequest> parameters;
	
	public EquipmentRequest() {

	}
	
	public EquipmentRequest(String name, String description, Boolean isValid, String imageUrl,
			Long categoryId, Set<ParameterRequest> parameters) {
		this.name = name;
		this.description = description;
		this.isValid = isValid;
		this.imageUrl = imageUrl;
		this.categoryId = categoryId;
		this.parameters = parameters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Set<ParameterRequest> getParameters() {
		return parameters;
	}
	
}
