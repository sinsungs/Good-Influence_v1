package com.youtubers.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youtubers.dto.MeetUserDTO;
import com.youtubers.entity.Meet;
import com.youtubers.entity.MeetUser;
import com.youtubers.entity.User;
import com.youtubers.repository.MeetRepository;
import com.youtubers.repository.MeetUserRepository;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MeetUserService {
	
	private final UserRepository userRepository;
	private final MeetRepository meetRepository;
	private final MeetUserRepository meetUserRepository;

	@Transactional
    public String registerMeet(MeetUserDTO dto) {
    	
        User user = userRepository.findById(dto.getUserid()).orElse(null);
        Meet meet = meetRepository.findById(dto.getMeetid()).orElse(null);
        
        if (meetUserRepository.existsByUserAndMeet(user, meet)) {
            return "이미 참가한 모임입니다.";
        }
        
        // 신청 마감 처리 
        if (meet.getCurrentPlayers() >= meet.getMaxPlayers()) {
            return "이미 모임이 가득 찼습니다.";
        }
        
        MeetUser meetuser = MeetUser.builder()
        		.user(user)
        		.meet(meet)
        		.build();
    	
        meetUserRepository.save(meetuser);
        
        // 로직 추가 
        // currentPlayers 값 증가
        meet.setCurrentPlayers(meet.getCurrentPlayers() + 1);

        // currentPlayers와 maxPlayers 비교하여 result 값 설정
        if (meet.getCurrentPlayers() >= meet.getMaxPlayers()) {
            meet.setResult("마감");
        } else {
            meet.setResult("신청가능");
        }

        meetRepository.save(meet); // 업데이트된 Meet 객체 저장

//        System.out.println(result);
        return "성공했습니다";
        
    }
    
//    public String createPost(PostRequestDTO dto) {
//    	
//        Post post = postRepository.findById(dto.getPno()).get();
//        Youtuber youtuber = youtuberRepository.findById(dto.getYno()).get();
//        
//        PostYoutuber postYoutuber = new PostYoutuber();
//        postYoutuber.setPost(post);
//        postYoutuber.setYoutuber(youtuber);
//        postyoutuberRepository.save(postYoutuber);
//        
//        String response = String.format("%s번 게시물에 %s 유튜버를 등록하였습니다.", post.getPno(), youtuber.getName()); 
//        return response;
////        return postYoutuber;
//        
//        // 유튜버 여러명 받는 버전 만들어야 함 이거 할려면 dto yno를 List 형태로 만들어야 함 
////        List<Long> youTuberIds = dto.getYouTuberIds(); // Assuming you receive YouTuber IDs in the request
////        for (Long youTuberId : youTuberIds) {
////            Intermediate intermediate = new Intermediate(postId, youTuberId); // Assuming Intermediate entity with postId and youTuberId fields
////            intermediateRepository.save(intermediate);
////        }
//    }
    
    
    

}
