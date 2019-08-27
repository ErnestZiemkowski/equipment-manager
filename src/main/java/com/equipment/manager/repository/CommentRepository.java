package com.equipment.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.equipment.manager.model.Comment;
import com.equipment.manager.model.Equipment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query(value = "SELECT c FROM Comment c JOIN FETCH c.equipment e JOIN FETCH c.user u WHERE e.id = :equipmentId")
	List<Comment> findByEquipmentId(@Param("equipmentId") Long equipmentId);
}
