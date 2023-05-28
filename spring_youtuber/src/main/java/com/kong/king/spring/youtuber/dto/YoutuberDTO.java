package com.kong.king.spring.youtuber.dto;

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
public class YoutuberDTO {
	
    private Long yno;
    private String name;
    private String title;
    private String content;
    private int likes;
	private String writerEmail;
	private String writerName;

    // Constructors, getters, and setters
}