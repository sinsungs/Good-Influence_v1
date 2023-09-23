package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youtubers.entity.PostInfluencer;

public interface PostInfluencerRepository extends JpaRepository<PostInfluencer, Long> {

    @Query("SELECT COUNT(pi) FROM PostInfluencer pi")
	int recommnedRank();


}
