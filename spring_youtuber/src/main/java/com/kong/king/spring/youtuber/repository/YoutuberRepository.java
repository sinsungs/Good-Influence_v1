package com.kong.king.spring.youtuber.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kong.king.spring.youtuber.entity.Youtuber;

public interface YoutuberRepository extends JpaRepository<Youtuber, Long> {


	
}
