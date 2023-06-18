package com.kong.king.spring.youtuber.service;

import com.kong.king.spring.youtuber.dto.PostRequestDTO;

public interface PostYoutuberService {
	
	String createPost(PostRequestDTO dto);
	
	String getList();
	
//	boolean addYouTubersToPost(Long postId, List<Long> youtuberIds);
//	
//	PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
//	
//	BoardDTO get(Long bno);
//	
//	void removeWithReplies(Long bno);
//	
//	void modify(BoardDTO boardDTO);
	
//	String createPost(PostRequestDTO dto);
//	
//	String getList();
	
	
//    default Post dtoToEntity(PostRequestDTO dto) {
//        Youtuber youtuber = Youtuber.builder()
//                .yno(dto.getYno())
//                .build();
//
//        Post post = Post.builder()
//                .pno(dto.getPno())
////                .title(dto.getTitle())
////                .content(dto.getContent())
//                .build();
//
//        PostYoutuber postYoutuber = PostYoutuber.builder()
//                .post(post)
//                .youtuber(youtuber)
//                .build();
//
//        post.addPostYoutuber(postYoutuber);
//
//        return post;
//    }
//    
//	
//	default Board dtoToEntity(BoardDTO dto) {
//		Member member = Member.builder()
//				.email(dto.getWriterEmail()).build();
//		
//		Board board = Board.builder()
//				.bno(dto.getBno())
//				.title(dto.getTitle())
//				.content(dto.getContent())
//				.writer(member)
//				.build();
//		
//		return board;
//	}


	
//	
//	default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {
//		
//		BoardDTO boardDTO = BoardDTO.builder()
//				.bno(board.getBno())
//				.title(board.getTitle())
//				.content(board.getContent())
//				.regDate(board.getRegDate())
//				.modDate(board.getModDate())
//				.writerEmail(member.getEmail())
//				.writerName(member.getName())
//				.replyCount(replyCount.intValue())
//				.build();
//		
//		return boardDTO;
//		
//	}

}
