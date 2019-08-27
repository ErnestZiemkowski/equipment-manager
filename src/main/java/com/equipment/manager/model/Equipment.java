package com.equipment.manager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "equipments")
public class Equipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 40)	
	private String name;
	
	@NotBlank
	@Size(max = 250)
	private String description;
	
	private Boolean isValid = true;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(
			mappedBy = "equipment",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private Set<Comment> comments = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToOne
	@JoinColumn(name = "specification_id")
	private Specification specification;
	
	public Equipment() {
	
	}
	
	public Equipment(String name, String description, Boolean isValid, String imageUrl) {
		this.name = name;
		this.description = description;
		this.isValid = isValid;
		this.imageUrl = imageUrl;
		this.specification = new Specification();
		this.specification.setEquipment(this);
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

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Boolean getIsValid() {
		return isValid;
	}


	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}


	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Comment> getComments() {
		return comments;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setEquipment(this);
	}

	public void removeComment(Comment comment) {
		comments.remove(comment);
		comment.setEquipment(null);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}
	
}
