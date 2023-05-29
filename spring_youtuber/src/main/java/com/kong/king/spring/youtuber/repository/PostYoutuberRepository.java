package com.kong.king.spring.youtuber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kong.king.spring.youtuber.entity.PostYoutuber;

public interface PostYoutuberRepository extends JpaRepository<PostYoutuber, Long> {
	
}
