package com.equipment.manager.payload;

public class EquipmentResponse {
	
	private Long id;
	private String name;
	private String description;
	private Boolean isValid;
	private String imageUrl;
		
	public EquipmentResponse(Long id, String name, String description, Boolean isValid, String imageUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isValid = isValid;
		this.imageUrl = imageUrl;
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
	
}
