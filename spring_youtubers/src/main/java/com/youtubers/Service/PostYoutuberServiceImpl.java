package com.youtubers.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youtubers.dto.PostRequestDTO;
import com.youtubers.dto.PostYoutuberDTO;
import com.youtubers.entity.Post;
import com.youtubers.entity.PostYoutuber;
import com.youtubers.entity.Youtuber;
import com.youtubers.repository.PostRepository;
import com.youtubers.repository.PostYoutuberRepository;
import com.youtubers.repository.YoutuberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostYoutuberServiceImpl implements PostYoutuberService {
	
	private final PostRepository postRepository;
	private final YoutuberRepository youtuberRepository;
	private final PostYoutuberRepository postyoutuberRepository;
	
//    @Override
//    public Post createPost(PostRequestDTO dto) {
//        Post post = dtoToEntity(dto);
//        return postRepository.save(post);
//    }
    
//    @Override
//    public Post createPost(PostRequestDTO dto) {
//        Post post = dtoToEntity(dto);
//        post = postRepository.save(post); // Post 저장
//
//        // Post와 연관된 PostYoutuber 엔티티 저장
//        for (PostYoutuber postYoutuber : post.getPostYoutubers()) {
//            postYoutuber.setPost(post);
//            postyoutuberRepository.save(postYoutuber);
//        }
//
//        return post;
//    }
    
    @Override
    public String createPost(PostRequestDTO dto) {
    	
        Post post = postRepository.findById(dto.getPno()).get();
        Youtuber youtuber = youtuberRepository.findById(dto.getYno()).get();
        
        PostYoutuber postYoutuber = new PostYoutuber();
        postYoutuber.setPost(post);
        postYoutuber.setYoutuber(youtuber);
        postyoutuberRepository.save(postYoutuber);
        
        String response = String.format("%s번 게시물에 %s 유튜버를 등록하였습니다.", post.getPno(), youtuber.getName()); 
        return response;
//        return postYoutuber;
        
        // 유튜버 여러명 받는 버전 만들어야 함 이거 할려면 dto yno를 List 형태로 만들어야 함 
//        List<Long> youTuberIds = dto.getYouTuberIds(); // Assuming you receive YouTuber IDs in the request
//        for (Long youTuberId : youTuberIds) {
//            Intermediate intermediate = new Intermediate(postId, youTuberId); // Assuming Intermediate entity with postId and youTuberId fields
//            intermediateRepository.save(intermediate);
//        }
    }
    
    
	@Override
	public String getList() {
		List<PostYoutuber> postYoutubers = postyoutuberRepository.findAll();
		
		List<PostYoutuberDTO> dto = new ArrayList<>();
		
		for(PostYoutuber postYoutuber : postYoutubers) {
			dto.add(PostYoutuberDTO.of(postYoutuber));
		}
		
		String response = dto.toString();
			
		return response;
		
		
//      List<ExamineeAcademy> examineeAcademies = examineeAcademyRepository.findAll();
//
//      List<ExamineeAcademyDTO> dto = new ArrayList<>();
//      for(ExamineeAcademy examineeAcademy : examineeAcademies) {
//          dto.add(ExamineeAcademyDTO.of(examineeAcademy));
//      }
	}


//	@Override
//	public String get(Long pyno) {
//		// TODO Auto-generated method stub
//		PostYoutuber postyoutuber = postyoutuberRepository.findById(pyno);
//				
//	}
	
	
//    @GetMapping("/list-academy")
//    public ResponseEntity<String> showRegisterAcademy() {
//        List<ExamineeAcademy> examineeAcademies = examineeAcademyRepository.findAll();
//
//        List<ExamineeAcademyDTO> dto = new ArrayList<>();
//        for(ExamineeAcademy examineeAcademy : examineeAcademies) {
//            dto.add(ExamineeAcademyDTO.of(examineeAcademy));
//        }
//
//        String response = dto.toString();
//        return ResponseEntity.ok(response);
//    }
//    
	

	

//	@Override
//	public boolean addYouTubersToPost(Long postId, List<Long> youtuberIds) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
//	public boolean addYouTubersToPost(Long postId, List<Long> youtuberIds) {
//	    Optional<Post> optionalPost = postRepository.findById(postId);
//	    if (optionalPost.isPresent()) {
//	        Post post = optionalPost.get();
//	        
//	        List<Youtuber> youtubers = new ArrayList<>();
//	        
//	        for (Long youtuberId : youtuberIds) {
//	            Optional<Youtuber> optionalYouTuber = youtuberRepository.findById(youtuberId);
//	            if (optionalYouTuber.isPresent()) {
//	            	
//	                Youtuber youtuber = optionalYouTuber.get();
//	                youtubers.add(youtuber);
//	                
//	            } else {
//	                // 해당 YouTuber ID에 해당하는 YouTuber가 없는 경우 처리 로직 작성
//	            }
//	        }
//	        
//	        post.getPostYoutubers().add((PostYoutuber) youtubers);
//	        postRepository.save(post);
//	        
//	        return true;
//	    } else {
//	        return false;
//	    }
//	}

}
