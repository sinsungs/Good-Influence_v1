package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.Meet;
import com.youtubers.entity.Orders;
import com.youtubers.entity.User;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	void deleteByUserAndMeet(User user, Meet meet);

    
}
	

