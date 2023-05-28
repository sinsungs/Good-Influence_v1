package com.kong.king.spring.youtuber.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kong.king.spring.youtuber.dto.BoardDTO;
import com.kong.king.spring.youtuber.dto.PageRequestDTO;
import com.kong.king.spring.youtuber.dto.PageResultDTO;
import com.kong.king.spring.youtuber.entity.Board;
import com.kong.king.spring.youtuber.entity.Member;
import com.kong.king.spring.youtuber.repository.BoardRepository;
import com.kong.king.spring.youtuber.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	
	private final ReplyRepository replyRepository;
	
	// 게시물 등록 
	@Override
	public Long register(BoardDTO dto) {
		log.info("---BoardServiceImpl register()---" + dto);
		Board board = dtoToEntity(dto);
		boardRepository.save(board);
		
		return board.getBno();
	}

	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info(pageRequestDTO);
		
		Function<Object[], BoardDTO> fn = ( en -> entityToDTO((Board)en[0], (Member)en[1], (Long)en[2]));
		
		Page<Object[]> result = boardRepository.searchPage(
				pageRequestDTO.getType(),
				pageRequestDTO.getKeyword(),
				pageRequestDTO.getPageable(Sort.by("bno").descending()));
		
		return new PageResultDTO<>(result, fn);
	}

	
	@Override
	public BoardDTO get(Long bno) {
		Object result = boardRepository.getBoardByBno(bno);
		Object[] arr = (Object[])result;
		
		return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}
	
	@Transactional
	@Override
	public void removeWithReplies(Long bno) {
		replyRepository.deleteByBno(bno);
		
		boardRepository.deleteById(bno);
	}

	@Override
	public void modify(BoardDTO boardDTO) {
		
		Board board = boardRepository.getOne(boardDTO.getBno());
		
		board.changeTitle(boardDTO.getTitle());
		board.changeContent(boardDTO.getContent());
		
		System.out.println("board--------" + board);
		
		boardRepository.save(board);
	}

}
