package com.youtubers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
import com.youtubers.entity.User;

public interface MeetUserRepository extends JpaRepository<MeetUser, Long> {
	
    @Query("SELECT COUNT(mu.user) FROM MeetUser mu")
	int countMeetUser();
    
    
//    @Query("SELECT mu.user FROM MeetUser mu " +
//            "GROUP BY mu.user " +
//            "ORDER BY COUNT(mu.meet) DESC")
//     List<User> findTop5UsersWithMostMeets();
    
    @Query("SELECT mu.user, COUNT(mu.meet)" +
    		"FROM MeetUser mu " +
            "GROUP BY mu.user " +
            "ORDER BY COUNT(mu.meet) DESC")
     List<Object[]> findTop5UsersWithMostMeets();
    
//boolean existsByUserAndMeet(User user, Meet meet);
	MeetUser findByUserAndMeet(User user, Meet meet);
    


//	List<MeetUser> findByUserUserId(Long userId);

}
