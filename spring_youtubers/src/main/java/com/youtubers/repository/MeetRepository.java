package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youtubers.entity.Meet;

public interface MeetRepository extends JpaRepository<Meet, Long> {

    @Query("SELECT COUNT(m) FROM Meet m")
	int countMeet();

    @Query("SELECT COUNT(mu) FROM MeetUser mu")
	int meetRank();
}
