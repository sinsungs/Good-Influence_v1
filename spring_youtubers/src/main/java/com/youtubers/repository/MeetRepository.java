package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.Meet;

public interface MeetRepository extends JpaRepository<Meet, Long> {

}