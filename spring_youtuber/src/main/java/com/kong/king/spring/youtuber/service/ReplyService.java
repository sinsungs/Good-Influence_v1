package com.kong.king.spring.youtuber.service;

import java.util.List;

import com.kong.king.spring.youtuber.dto.ReplyDTO;
import com.kong.king.spring.youtuber.entity.Board;
import com.kong.king.spring.youtuber.entity.Reply;

public interface ReplyService {
	
	Long register(ReplyDTO replyDTO);
	List<ReplyDTO> getList(Long bno);
	void modify(ReplyDTO replyDTO);
	void remove(Long rno);
	
	default Reply dtoToEntity(ReplyDTO replyDTO) {
		Board board = Board.builder().bno(replyDTO.getBno()).build();
		
		Reply reply = Reply.builder()
				.rno(replyDTO.getRno())
				.text(replyDTO.getText())
				.replyer(replyDTO.getReplyer())
				.board(board)
				.build();
		
		return reply;
	}
	
	default ReplyDTO entityToDTO(Reply reply) {
		ReplyDTO dto = ReplyDTO.builder()
				.rno(reply.getRno())
				.text(reply.getText())
				.replyer(reply.getReplyer())
				.regDate(reply.getRegDate())
				.modDate(reply.getModDate())
				.build();
		
		return dto;
	}
	
}
