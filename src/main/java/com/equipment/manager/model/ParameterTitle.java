package com.equipment.manager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(
		name = "parameter_titles",
		uniqueConstraints = {
			@UniqueConstraint(
				columnNames = {
					"title"
			})
})
public class ParameterTitle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@OneToMany(
		mappedBy = "parameterTitle",
		cascade = {
				CascadeType.DETACH,
				CascadeType.MERGE,
				CascadeType.PERSIST,
				CascadeType.REFRESH
	})
	private Set<Parameter> parameters = new HashSet<>();
	
	public ParameterTitle(String title) {
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

	public Set<Parameter> getParameters() {
		return parameters;
	}
	
	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
		parameter.setParameterTitle(this);
	}

	public void removeParameter(Parameter parameter) {
		parameters.remove(parameter);
		parameter.setParameterTitle(null);
	}

}
