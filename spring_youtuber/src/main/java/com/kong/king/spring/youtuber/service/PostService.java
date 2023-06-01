package com.kong.king.spring.youtuber.service;

import com.kong.king.spring.youtuber.dto.PostDTO;
import com.kong.king.spring.youtuber.entity.Post;

public interface PostService {

	void createPost(PostDTO dto);
//	
//	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
//	
//	BoardDTO get(Long bno);
//	
//	void removeWithReplies(Long bno);
//	
//	void modify(BoardDTO boardDTO);
//	
	
	default Post dtoToEntity(PostDTO dto) {
		
		Post post = Post.builder()
				.pno(dto.getPno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		
		return post;
	}
	
//	
//	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
//		
//		BoardDTO boardDTO = BoardDTO.builder()
//				.bno(board.getBno())
//				.title(board.getTitle())
//				.content(board.getContent())
//				.regDate(board.getRegDate())
//				.modDate(board.getModDate())
//				.writerEmail(member.getEmail())
//				.writerName(member.getName())
//				.replyCount(replyCount.intValue())
//				.build();
//		
//		return boardDTO;
//		
//	}
    
}

