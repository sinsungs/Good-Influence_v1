package com.youtubers.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
//	@CreationTimestamp // 시간이 자동 입력
//	private Timestamp createDate;
    
    @OneToMany(mappedBy = "meet")
    private List<MeetUser> meetUser = new ArrayList<>();


}

