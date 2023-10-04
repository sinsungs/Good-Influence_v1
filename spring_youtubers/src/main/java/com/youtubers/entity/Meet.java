package com.youtubers.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meet extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetid;
	
	private String title;
	private String content;	
	
    private int maxPlayers;
    private int currentPlayers;
    
    private String region;
    private String result;
    
	private LocalDateTime meettime;
    
//    @OneToMany(mappedBy = "meet")
//    private List<MeetUser> meetUser = new ArrayList<>();
    
    @ManyToOne 
    @JoinColumn(name = "user_id") // 외래키를 가리킬 컬럼 이름 지정
    private User user;

    @OneToMany(mappedBy = "meet", cascade = CascadeType.ALL)
    private List<MeetUser> meetUsers = new ArrayList<>();
    
    @OneToMany(mappedBy = "meet", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();
    
}

