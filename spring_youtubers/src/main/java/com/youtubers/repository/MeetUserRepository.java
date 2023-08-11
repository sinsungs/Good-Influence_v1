package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.MeetUser;

public interface MeetUserRepository extends JpaRepository<MeetUser, Long> {

}
