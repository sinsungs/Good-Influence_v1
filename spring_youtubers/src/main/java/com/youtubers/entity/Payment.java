package com.youtubers.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 테이블 생성 효과
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payid;
	
	private int deposit;
	
	private String paytype;
	
	@CreationTimestamp // 시간이 자동 입력
    private Timestamp paymentDate;
	
    @ManyToOne // Payment와 User는 N:1 관계
    @JoinColumn(name = "user_id") // 외래키를 가리킬 컬럼 이름 지정
    private User user;
	

}
