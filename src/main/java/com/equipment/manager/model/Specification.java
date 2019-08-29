package com.equipment.manager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "specifications")
public class Specification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(
			mappedBy = "specification",
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	private Equipment equipment;

	@ManyToMany(cascade = {
			CascadeType.MERGE,
			CascadeType.PERSIST,
	})
	@JoinTable(
			name = "specification_parameters",
			joinColumns = @JoinColumn(name = "specification_id"),
			inverseJoinColumns = @JoinColumn(name = "parameter_id")
	)
	private Set<Parameter> parameters = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
