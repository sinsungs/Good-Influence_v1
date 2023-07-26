package com.youtubers.Service;

import org.springframework.stereotype.Service;

import com.youtubers.entity.Follow;
import com.youtubers.entity.User;
import com.youtubers.repository.FollowRepository;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FollowService {

	private final FollowRepository followRepository;
	private final UserRepository userRepository;
	
    public void followUser(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId).orElse(null);
        User following = userRepository.findById(followingId).orElse(null);
        
        if (follower != null && following != null) {
            Follow follow = new Follow();
            follow.setFollower(follower);
            follow.setFollowing(following);
            followRepository.save(follow);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
