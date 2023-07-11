package com.youtubers.Service;

import java.util.List;

import com.youtubers.dto.YoutuberDTO;
import com.youtubers.entity.Member;
import com.youtubers.entity.Youtuber;

public interface YoutuberService {
	
	Youtuber createYoutuber(YoutuberDTO dto);
	
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
