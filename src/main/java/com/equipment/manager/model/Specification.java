package com.equipment.manager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "specifications")
public class Specification {
	
	@OneToOne(mappedBy = "specification")
	private Equipment equipment;

	@ManyToMany(cascade = {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
	})
	@JoinTable(
			name = "specification_parameters",
			joinColumns = @JoinColumn(name = "id"),
			inverseJoinColumns = @JoinColumn(name = "id")
	)
	private Set<Parameter> parameters = new HashSet<>();
	
	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public Set<Parameter> getParameters() {
		return parameters;
	}
	
	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
		parameter.getSpecifications().add(this);
	}

	public void removeParameter(Parameter parameter) {
		parameters.remove(parameter);
		parameter.getSpecifications().remove(this);
	}

}
