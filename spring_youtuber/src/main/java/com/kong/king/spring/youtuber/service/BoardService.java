package com.kong.king.spring.youtuber.service;

import com.kong.king.spring.youtuber.dto.BoardDTO;
import com.kong.king.spring.youtuber.dto.PageRequestDTO;
import com.kong.king.spring.youtuber.dto.PageResultDTO;
import com.kong.king.spring.youtuber.entity.Board;
import com.kong.king.spring.youtuber.entity.Member;

public interface BoardService {
	
	Long register(BoardDTO dto);
	
	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	BoardDTO get(Long bno);
	
	void removeWithReplies(Long bno);
	
	void modify(BoardDTO boardDTO);
	
	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder()
				.email(dto.getWriterEmail()).build();
		
		Board board = Board.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(member)
				.build();
		
		return board;
	}
	
	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
		
		BoardDTO boardDTO = BoardDTO.builder()
				.bno(board.getBno())
				.title(board.getTitle())
				.content(board.getContent())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.writerEmail(member.getEmail())
				.writerName(member.getName())
				.replyCount(replyCount.intValue())
				.build();
		
		return boardDTO;
		
	}
}
