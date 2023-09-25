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
public class RankDTO {
	


//    private int meetRank;
//    private int recommnedRank;
	private List<User> meetRanks;
    private List<Influencer> recommendRank;

    

    // Constructors, getters, and setters
}