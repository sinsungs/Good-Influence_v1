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
public class MeetDTO {
	
    private Long meetid;
    private String title;
    private String content;
    private String startTime;
    private int maxPlayers;
    private int currentPlayers;
    private String region;
    private String result;
}
