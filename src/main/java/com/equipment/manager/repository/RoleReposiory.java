package com.equipment.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipment.manager.model.Role;
import com.equipment.manager.model.RoleName;

@Repository
public interface RoleReposiory extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}
