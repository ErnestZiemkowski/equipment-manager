package com.equipment.manager.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 30)	
	private String name;

	@OneToMany(
		mappedBy = "category",
		cascade = {
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			CascadeType.MERGE,
			CascadeType.DETACH
		})
	private Set<Equipment> equipments;
	
	public Category() {
	
	}

	public Category(String name) {
		this.name = name;
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

	public Set<Equipment> getEquipments() {
		return equipments;
	}
	
	public void addEquipment(Equipment equipment) {
		equipments.add(equipment);
		equipment.setCategory(this);
	}
	
	public void removeEquipment(Equipment equipment) {
		equipments.remove(equipment);
		equipment.setCategory(null);
	}
	
}
