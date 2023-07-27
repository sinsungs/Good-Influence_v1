package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

	Follow findByFollowerIdAndFollowingId(Long followerId, Long followingId);

}
