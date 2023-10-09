package com.youtubers.dto;

import com.youtubers.entity.PostInfluencer;

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
public class PostResponseDTO {
	
	private Long pno;
	private String title;
	private String content;

    private Long ino;
    private String name;
    
    
    private Long secondino;
    private Long thirdino;

    private String writer;
    
    private String imageurl;
}
