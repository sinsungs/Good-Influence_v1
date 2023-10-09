package com.youtubers.dto;

import java.time.LocalDateTime;

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
    private int maxplayers;
    private int currentPlayers;
    private String region;
    private String result;
    private String writer;
	private LocalDateTime meettime;
}
