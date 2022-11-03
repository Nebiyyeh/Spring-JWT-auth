package com.neb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.neb.domain.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long>  {
	
	@EntityGraph(attributePaths = "roles")  //to get the roles eagerly
	Optional<MyUser> findByUserName(String userName);

}
