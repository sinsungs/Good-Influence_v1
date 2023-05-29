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
public class PostDTO {
	
	private Long pno;
	private String title;
	private String content;
	private String writerEmail;
	private String writerName;
	private LocalDateTime regDate;
	private LocalDateTime modDate;

}
