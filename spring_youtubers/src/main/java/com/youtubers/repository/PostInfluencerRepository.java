package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.PostInfluencer;

public interface PostInfluencerRepository extends JpaRepository<PostInfluencer, Long> {
	
}
