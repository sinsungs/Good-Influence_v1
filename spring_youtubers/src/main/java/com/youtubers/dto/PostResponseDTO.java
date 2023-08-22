package com.youtubers.dto;

import com.youtubers.entity.PostInfluencer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//ExamineeAcademyDto
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {
//private Long pyno;
	
private String postTitle;
private String postContent;

private String influencerName;
private String influencerContent;

public static PostResponseDTO of(PostInfluencer postInfluencer) {
   return PostResponseDTO.builder()
		   
		   .postTitle(postInfluencer.getPost().getTitle())
           .postContent(postInfluencer.getPost().getContent())
           .influencerName(postInfluencer.getInfluencer().getName())
           .influencerContent(postInfluencer.getInfluencer().getContent())
           
           .build();
}
}


//public class ExamineeAcademyDTO {
//	 private String examineeName;
//	 private String academyName;
//	 private LocalDateTime registerDate;
//
//	 public static ExamineeAcademyDTO of(ExamineeAcademy examineeAcademy) {
//	     return ExamineeAcademyDTO.builder()
//	             .examineeName(examineeAcademy.getExaminee().getName())
//	             .academyName(examineeAcademy.getAcademy().getName())
//	             .registerDate(examineeAcademy.getRegisterDate())
//	             .build();
//	 }
//	}
