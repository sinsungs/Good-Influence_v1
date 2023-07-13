package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.PostYoutuber;

public interface PostYoutuberRepository extends JpaRepository<PostYoutuber, Long> {
	
}
