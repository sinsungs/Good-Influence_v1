package com.kong.king.spring.youtuber.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kong.king.spring.youtuber.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByLoginId(String loginId);
	boolean existsByNickname(String nickname);
	Optional<User> findByLoginId(String loginId);
	

}
