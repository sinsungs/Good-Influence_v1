package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
