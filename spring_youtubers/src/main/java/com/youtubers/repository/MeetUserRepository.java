package com.youtubers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
import com.youtubers.entity.User;

public interface MeetUserRepository extends JpaRepository<MeetUser, Long> {
	
	boolean existsByUserAndMeet(User user, Meet meet);

//	List<MeetUser> findByUserUserId(Long userId);

}
