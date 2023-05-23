package com.kong.king.spring.youtuber.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kong.king.spring.youtuber.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {


	
}
