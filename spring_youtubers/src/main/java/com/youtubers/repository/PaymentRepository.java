package com.youtubers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youtubers.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("SELECT SUM(p.deposit) FROM Payment p")
	int TotalDeposit();

//    // 오늘 날짜에 해당하는 "deposit" 값을 합산하여 조회하는 메서드
//    @Query("SELECT SUM(p.deposit) FROM Payment p WHERE DATE(p.payment_date) = DATE(CURRENT_DATE)")
//    Double TotalDepositToday();
    
}
	

