package com.youtubers.dto;

import com.youtubers.entity.PostYoutuber;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

//ExamineeAcademyDto
@AllArgsConstructor
@Builder
@ToString
public class PostYoutuberDTO {
//private Long pyno;
private String postName;
private String youtuberName;
private String youtuberContent;

public static PostYoutuberDTO of(PostYoutuber postyoutuber) {
   return PostYoutuberDTO.builder()
//           .pyno(postyoutuber.getPyno())
           .postName(postyoutuber.getPost().getContent())
           .youtuberName(postyoutuber.getYoutuber().getName())
           .youtuberContent(postyoutuber.getYoutuber().getContent())
           
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
