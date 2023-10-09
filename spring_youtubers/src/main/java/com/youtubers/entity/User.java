package com.youtubers.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class User extends BaseEntity {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement 
	private Long id;
	
	@Column(nullable=false, length=100)
	private String username;
	
	@Column(nullable=false, length=100) // 해쉬를 넣기에 100으로 설정
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다.
	
	private String oauth;
	
	private int amount;
	
//	@CreationTimestamp // 시간이 자동 입력
//	private Timestamp createDate;
	
	private int experience;
	
	private String sns;
	
	private String imageUrl;
	
	
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Meet> meets = new ArrayList<>();
//    
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<MeetUser> meetusers = new ArrayList<>();
//    
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Post> posts = new ArrayList<>();
//	
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Payment> payments = new ArrayList<>();
//    
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Orders> orders = new ArrayList<>();
	
    // Reward를 참조(조인)하는 부분 추가
//    @ManyToOne
//    @JoinColumn(name = "rankid")
//    private Ranks ranks;


}
