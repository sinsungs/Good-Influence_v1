package com.youtubers.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.youtubers.dto.InfluencerDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.repository.InfluencerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class InfluencerService{

	private final InfluencerRepository influencerRepository;
	
    @Value("${upload.dir}")
    private String uploadDir;
	
// 인플루언서 등록 
//	public Influencer createInfluencer(InfluencerDTO dto,  MultipartFile imageFile) throws IOException {
	public Influencer createInfluencer(InfluencerDTO dto) {
		
		log.info("---BoardServiceImpl register()---" + dto);
		
//        if (imageFile != null && !imageFile.isEmpty()) {
//        	
//	   		 String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//	         Path filePath = Paths.get(uploadDir, fileName);
//	         Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//	         dto.setImage_url(filePath.toString()); // 이미지 파일 경로를 엔티티에 저장
//        }
		
		Influencer influencer = dtoToEntity(dto);
		
		return influencerRepository.save(influencer); 
		
	}
	
//    public List<Youtuber> getAllYoutubers() {
//        return youtuberRepository.findAll();
//    }
	
//	public List<InfluencerDTO> getAllYoutubersWithWriters() {
//	    List<Object[]> results = youtuberRepository.getAllYoutubersWithWriters();
//	    List<InfluencerDTO> youtubers = new ArrayList<>();
//
//	    for (Object[] arr : results) {
//	        Youtuber youtuber = (Youtuber) arr[0];
//	        Member writer = (Member) arr[1];
//	        youtubers.add(entityToDTO(youtuber, writer));
//	    }
//
//	    return youtubers;
//	}  
	
//	public InfluencerDTO getYoutuberWithWriter(Long yno) {
//		Object result = youtuberRepository.getYoutuberWithWriter(yno);
//		Object[] arr = (Object[])result;
//		
//		return entityToDTO((Youtuber)arr[0], (Member)arr[1]);
//	}
	
//	public void modify(InfluencerDTO dto) {
//		
//		Youtuber youtuber = youtuberRepository.getOne(dto.getYno());
//		
//		System.out.println("youtuber1--------" + youtuber);
//		
////		youtuber.changeTitle(dto.getTitle());
////		youtuber.changeContent(dto.getContent());
//		
//		System.out.println("youtuber2--------" + youtuber);
//		
//		youtuberRepository.save(youtuber);
//	}
	/* 이렇게 수정하면 member와의 연관관계를 이어주지 않아도 youtuber의 엔티티만 수정가능 하게된다 */
	
	
	
//	@Override
//	public void modify(YoutuberDTO dto) {
//		Youtuber youtuber = dtoToEntity(dto);
//		youtuberRepository.save(youtuber);
//	}
	
//	public void remove(Long yno) {
//		youtuberRepository.deleteById(yno);
//		
//	}


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
	
	Influencer dtoToEntity(InfluencerDTO dto) {
//		Member member = Member.builder()
//				.email(dto.getWriterEmail()).build();

		Influencer influencer = Influencer.builder()
				.ino(dto.getIno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.likes(dto.getLikes())
				.name(dto.getName())
//				.writer(member)
				.build();
		
		return influencer;
	}
	
//	InfluencerDTO entityToDTO(Influencer youtuber, Member member) {
	InfluencerDTO entityToDTO(Influencer influencer) {
		
		InfluencerDTO influencerDTO = InfluencerDTO.builder()
	            .ino(influencer.getIno())
	            .name(influencer.getName())
	            .title(influencer.getTitle())
	            .content(influencer.getContent())
	            .likes(influencer.getLikes())
//				.writerEmail(member.getEmail())
//				.writerName(member.getName())
	            
				.build();
		
		return influencerDTO;
		
	}
}
