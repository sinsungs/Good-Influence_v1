package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
