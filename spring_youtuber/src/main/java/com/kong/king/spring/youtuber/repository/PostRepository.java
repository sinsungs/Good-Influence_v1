package com.kong.king.spring.youtuber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kong.king.spring.youtuber.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
