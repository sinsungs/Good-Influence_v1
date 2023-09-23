package com.youtubers.dto;

import java.util.List;

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
	

    private List<User> meetRank;
    private int recommnedRank;

    

    // Constructors, getters, and setters
}