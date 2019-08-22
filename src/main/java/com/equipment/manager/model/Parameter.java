package com.equipment.manager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "parameters", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"name"
		})
})
public class Parameter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 30)	
	private String name;

	@NotBlank
	@Size(max = 150)
	private String value;
	
	@ManyToMany
	@JoinTable(
			name = "equipment_parameters",
			joinColumns = @JoinColumn(name = "parameter_id"),
			inverseJoinColumns = @JoinColumn(name = "equipment_id")
	)
	private Set<Equipment> equipments = new HashSet<>();
	
	public Parameter() {
	
	}

	public Parameter(String name, String value) {
		this.name = name;
		this.value = value;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Set<Equipment> getEquipments() {
		return equipments;
	}
	
	public void addEquipment(Equipment equipment) {
		equipments.add(equipment);
		equipment.getParameters().add(this);
	}
	
	public void removeEquipment(Equipment equipment) {
		equipments.remove(equipment);
		equipment.getParameters().remove(this);
	}
	
}
