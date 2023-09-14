package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youtubers.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT COUNT(p) FROM Post p")
	int countAllPost();
}
