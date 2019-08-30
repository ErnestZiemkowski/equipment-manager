package com.equipment.manager.payload;

public class ParameterResponse {
	
	private Long id;
	private ParameterTitleResponse parameterTitleResponse;
	private String description;
	
	public ParameterResponse(Long id, ParameterTitleResponse parameterTitleResponse, String description) {
		this.id = id;
		this.parameterTitleResponse = parameterTitleResponse;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ParameterTitleResponse getParameterTitleResponse() {
		return parameterTitleResponse;
	}

	public void setParameterTitleResponse(ParameterTitleResponse parameterTitleResponse) {
		this.parameterTitleResponse = parameterTitleResponse;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
