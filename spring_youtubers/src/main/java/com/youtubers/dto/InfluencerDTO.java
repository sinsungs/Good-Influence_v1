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
public class InfluencerDTO {
	
    private Long ino;
    private String name;
    private String title;
    private String content;
    private int likes;
    private String image_url;
    private String sns;
    private String category;
    private String verifyuser;
//	private String writerEmail;
//	private String writerName;

    // Constructors, getters, and setters
}