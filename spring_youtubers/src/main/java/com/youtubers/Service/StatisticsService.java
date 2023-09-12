package com.youtubers.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.youtubers.repository.InfluencerRepository;
import com.youtubers.repository.PostRepository;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatisticsService {
	
	private final InfluencerRepository influencerRepositoy;
	private final PostRepository postRepository;


	public int influenceCount() {
		// TODO Auto-generated method stub
		return influencerRepositoy.countAllInfluencers();
	}

	public int categoryCount() {
		// TODO Auto-generated method stub
		return influencerRepositoy.countInfluencersByCategory();
	}
	
	public int postCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int ranking() {
		// TODO Auto-generated method stub
		return 0;
	}

}
