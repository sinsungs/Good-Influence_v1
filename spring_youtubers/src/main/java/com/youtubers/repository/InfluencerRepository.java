package com.youtubers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.youtubers.entity.Influencer;

public interface InfluencerRepository extends JpaRepository<Influencer, Long> {

//	@Query("SELECT b, w " +
//			" FROM Board b LEFT JOIN b.writer w " +
//			" WHERE b.bno = :bno ")
//	Object getYoutuberByYno(@Param("yno") Long yno);
	
//	@Query("SELECT y, m FROM Youtuber y LEFT JOIN y.writer m WHERE y.yno = :yno")
//	Object getYoutuberWithWriter(@Param("yno") Long yno);
//	
//	@Query("SELECT y, m FROM Youtuber y LEFT JOIN y.writer m")
//	List<Object[]> getAllYoutubersWithWriters();
	
	List<Influencer> findByNameLike(String query);
}
