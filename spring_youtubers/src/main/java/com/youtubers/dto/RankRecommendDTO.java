package com.youtubers.dto;

import java.util.List;

import com.youtubers.entity.Influencer;
import com.youtubers.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankRecommendDTO {
	


	private Long id;
	private String name;
	private String sns;
	private int experience;
	private int recommendcount;
    
}