package com.kong.king.spring.youtuber.service;

import java.util.List;

import com.kong.king.spring.youtuber.dto.ReplyDTO;
import com.kong.king.spring.youtuber.dto.YoutuberDTO;
import com.kong.king.spring.youtuber.entity.Board;
import com.kong.king.spring.youtuber.entity.Member;
import com.kong.king.spring.youtuber.entity.Youtuber;

public interface YoutuberService {
	
	void createYoutuber(YoutuberDTO dto);
	
	YoutuberDTO getYoutuberWithWriter(Long yno);
	
	List<YoutuberDTO> getAllYoutubersWithWriters();
	
//	List<Youtuber> getAllYoutubers();
	
//	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
//	YoutuberDTO get(Long yno);
//	
//	void removeWithReplies(Long bno);
//	
//	void modify(BoardDTO boardDTO);
	
	void modify(YoutuberDTO dto);
	
	void remove(Long yno);
	
	
	
	default Youtuber dtoToEntity(YoutuberDTO dto) {
		Member member = Member.builder()
				.email(dto.getWriterEmail()).build();

		
		Youtuber youtuber = Youtuber.builder()
				.yno(dto.getYno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.likes(dto.getLikes())
				.name(dto.getName())
				.writer(member)
				.build();
		
		return youtuber;
	}
	
	default YoutuberDTO entityToDTO(Youtuber youtuber, Member member) {
		
		YoutuberDTO youtuberDTO = YoutuberDTO.builder()
	            .yno(youtuber.getYno())
	            .name(youtuber.getName())
	            .title(youtuber.getTitle())
	            .content(youtuber.getContent())
	            .likes(youtuber.getLikes())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
//				.replyCount(replyCount.intValue())
				.build();
		
		return youtuberDTO;
		
	}


}
