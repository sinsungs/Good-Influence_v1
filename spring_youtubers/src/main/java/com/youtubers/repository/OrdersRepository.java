package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    
}
	

