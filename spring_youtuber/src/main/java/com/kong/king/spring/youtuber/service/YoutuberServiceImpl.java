package com.kong.king.spring.youtuber.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kong.king.spring.youtuber.dto.BoardDTO;
import com.kong.king.spring.youtuber.dto.ReplyDTO;
import com.kong.king.spring.youtuber.dto.YoutuberDTO;
import com.kong.king.spring.youtuber.entity.Board;
import com.kong.king.spring.youtuber.entity.Member;
import com.kong.king.spring.youtuber.entity.Reply;
import com.kong.king.spring.youtuber.entity.Youtuber;
import com.kong.king.spring.youtuber.repository.YoutuberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class YoutuberServiceImpl implements YoutuberService {

	private final YoutuberRepository youtuberRepository;
	
	// 게시물 등록 
	@Override
	public Youtuber createYoutuber(YoutuberDTO dto) {
		log.info("---BoardServiceImpl register()---" + dto);
		Youtuber youtuber = dtoToEntity(dto);
		return youtuberRepository.save(youtuber);
		
//		return null;
	}
	
//    public List<Youtuber> getAllYoutubers() {
//        return youtuberRepository.findAll();
//    }
//    
	
	@Override
	public YoutuberDTO getYoutuberWithWriter(Long yno) {
		Object result = youtuberRepository.getYoutuberWithWriter(yno);
		Object[] arr = (Object[])result;
		
		return entityToDTO((Youtuber)arr[0], (Member)arr[1]);
	}
	
	@Override
	public void modify(YoutuberDTO dto) {
		Youtuber youtuber = dtoToEntity(dto);
		youtuberRepository.save(youtuber);
		
	}
	@Override
	public void remove(Long yno) {
		youtuberRepository.deleteById(yno);
		
	}


//	@Override
//	public BoardDTO get(Long bno) {
//		Object result = boardRepository.getBoardByBno(bno);
//		Object[] arr = (Object[])result;
//		
//		return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
//	}

//
//	@Override
//	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//		log.info(pageRequestDTO);
//		
//		Function<Object[], BoardDTO> fn = ( en -> entityToDTO((Board)en[0], (Member)en[1], (Long)en[2]));
//		
//		Page<Object[]> result = boardRepository.searchPage(
//				pageRequestDTO.getType(),
//				pageRequestDTO.getKeyword(),
//				pageRequestDTO.getPageable(Sort.by("bno").descending()));
//		
//		return new PageResultDTO<>(result, fn);
//	}
//
//	
//	@Override
//	public BoardDTO get(Long bno) {
//		Object result = boardRepository.getBoardByBno(bno);
//		Object[] arr = (Object[])result;
//		
//		return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
//	}
//	
//	@Transactional
//	@Override
//	public void removeWithReplies(Long bno) {
//		replyRepository.deleteByBno(bno);
//		
//		boardRepository.deleteById(bno);
//	}
//
//	@Override
//	public void modify(BoardDTO boardDTO) {
//		
//		Board board = boardRepository.getOne(boardDTO.getBno());
//		
//		board.changeTitle(boardDTO.getTitle());
//		board.changeContent(boardDTO.getContent());
//		
//		System.out.println("board--------" + board);
//		
//		boardRepository.save(board);
//	}
//
}
