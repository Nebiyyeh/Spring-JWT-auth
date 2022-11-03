package com.neb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neb.domain.Role;
import com.neb.domain.enums.RoleType;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByType(RoleType type);
}
