package com.equipment.manager.payload;

public class ParameterRequest {

	private Long parameterTitleId;
	private String description;
	
	public ParameterRequest(Long parameterTitleId, String description) {
		this.parameterTitleId = parameterTitleId;
		this.description = description;
	}
	
	public Long getParameterTitleId() {
		return parameterTitleId;
	}
	
	public void setParameterTitleId(Long parameterTitleId) {
		this.parameterTitleId = parameterTitleId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
		
}
