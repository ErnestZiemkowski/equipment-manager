package com.equipment.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipment.manager.model.ParameterTitle;

@Repository
public interface ParameterTitleRepository extends JpaRepository<ParameterTitle, Long> {

}
