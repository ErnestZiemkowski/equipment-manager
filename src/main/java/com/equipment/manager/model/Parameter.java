package com.equipment.manager.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parameters")
public class Parameter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(mappedBy = "specifications")
	private Set<Specification> specifications;

	@ManyToOne
	@JoinColumn(name = "title_id")
	private ParameterTitle parameterTitle;
	
	private String description;
	
	public Parameter(ParameterTitle parameterTitle, String description) {
		this.parameterTitle = parameterTitle;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Specification> getSpecifications() {
		return specifications;
	}

	public ParameterTitle getParameterTitle() {
		return parameterTitle;
	}

	public void setParameterTitle(ParameterTitle parameterTitle) {
		this.parameterTitle = parameterTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
