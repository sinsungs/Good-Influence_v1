package com.youtubers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
//	User findByUsernameAndPassword(String username, String password);

	Optional<User> findByEmail(String email);

}
