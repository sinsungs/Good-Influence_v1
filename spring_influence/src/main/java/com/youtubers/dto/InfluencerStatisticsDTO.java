package com.youtubers.dto;

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
public class InfluencerStatisticsDTO {
	

    private int influenceCount;
    private int categoryCount;
    private int postCount;
    private int meetCount;
    private int meetUserCount;
    private int totalDeposit;
    private Double totalDepositToday;
    
    private int ranking;
    

    // Constructors, getters, and setters
}
