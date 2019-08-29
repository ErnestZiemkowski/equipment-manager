package com.equipment.manager.model;

import java.util.HashSet;
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
	
	@ManyToMany(mappedBy = "parameters")
	private Set<Specification> specifications = new HashSet<>();

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
	
	public void addSpecification(Specification specification) {
		specifications.add(specification);
		specification.addParameter(this);
	}

	public void removeSpecification(Specification specification) {
		specifications.remove(specification);
		specification.removeParameter(this);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameter other = (Parameter) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
