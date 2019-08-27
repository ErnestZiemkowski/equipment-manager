package com.equipment.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipment.manager.model.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

}
