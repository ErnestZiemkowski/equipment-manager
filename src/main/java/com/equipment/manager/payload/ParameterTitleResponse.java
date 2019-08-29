package com.equipment.manager.payload;

public class ParameterTitleResponse {
	
	private Long id;
	private String title;
	
	public ParameterTitleResponse(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
