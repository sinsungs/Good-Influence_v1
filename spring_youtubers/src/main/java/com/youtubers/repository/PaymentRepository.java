package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubers.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
