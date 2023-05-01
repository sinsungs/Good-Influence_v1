package com.kong.king.spring.youtuber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kong.king.spring.youtuber.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long	> {

//	@Query("select b, w from Board b left join b.writer w where b.bno = :bno")
//	Object getBoardWithWriter(@Param("bno") Long bno);
//	
//	@Query("select b, r from Board b left join Reply r On r.board = b WHERE b.bno = bno")
//	List<Object[]> getBoardWithReply(@Param("bno") Long bno);
//	
//	@Query(value = "select b, w, count(r) " +
//					" from board b " +
//					" left join b.writer w " +
//					" left join reply r ON r.board = b " +
//					" GROUP BY b ",
//					countQuery = "SELECT count(b) from Board b")
//	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
//	
//	@Query("SELECT b, w, count(r) " +
//			" FROM Board b LEFT JOIN b.writer w " +
//			" LEFT JOIN Reply r ON r.board = b " +
//			" WHERE b.bno = :bno ")
//	Object getBoardByBno(@Param("bno") Long bno);
	
	
}
