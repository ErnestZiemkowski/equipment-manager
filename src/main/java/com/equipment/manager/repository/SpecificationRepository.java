package com.equipment.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipment.manager.model.Specification;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {

}
