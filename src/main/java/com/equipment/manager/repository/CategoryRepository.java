package com.equipment.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipment.manager.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
