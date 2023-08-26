package com.youtubers.entity;

import java.sql.Timestamp;
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
public class Meet{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meetid;
	
	private String title;
	private String content;	
    private String startTime;
    private int maxPlayers;
    private int currentPlayers;
    private String region;
    private String result;

    
    @OneToMany(mappedBy = "meet")
    private List<MeetUser> meetUser = new ArrayList<>();


}

