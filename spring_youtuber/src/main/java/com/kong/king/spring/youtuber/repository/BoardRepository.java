package com.kong.king.spring.youtuber.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kong.king.spring.youtuber.entity.Board;
import com.kong.king.spring.youtuber.repository.search.SearchBoardRepository;

public interface BoardRepository extends JpaRepository<Board, Long> , SearchBoardRepository {

	@Query("select b, w from Board b left join b.writer w where b.bno = :bno")
	Object getBoardWithWriter(@Param("bno") Long bno);
	
	@Query("select b, r from Board b left join Reply r ON r.board = b WHERE b.bno = :bno")
	List<Object[]> getBoardWithReply(@Param("bno") Long bno);
//	
	@Query(value = "select b, w, count(r) " +
					" from Board b " +
					" left join b.writer w " +
					" left join Reply r ON r.board = b " +
					" GROUP BY b ",
					countQuery = "SELECT count(b) from Board b")
	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
//	
	@Query("SELECT b, w, count(r) " +
			" FROM Board b LEFT JOIN b.writer w " +
			" LEFT JOIN Reply r ON r.board = b " +
			" WHERE b.bno = :bno ")
	Object getBoardByBno(@Param("bno") Long bno);
	
	
}
