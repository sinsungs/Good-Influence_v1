//package com.kong.king.spring.youtuber.service;
//
//import org.springframework.stereotype.Service;
//
//import com.kong.king.spring.youtuber.dto.PostYoutuberDTO;
//import com.kong.king.spring.youtuber.entity.Post;
//import com.kong.king.spring.youtuber.entity.PostYoutuber;
//import com.kong.king.spring.youtuber.entity.Youtuber;
//import com.kong.king.spring.youtuber.repository.PostRepository;
//import com.kong.king.spring.youtuber.repository.PostYoutuberRepository;
//import com.kong.king.spring.youtuber.repository.YoutuberRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class PostService {
//    private final PostRepository postRepository;
//    private final YoutuberRepository youtuberRepository;
//    private final PostYoutuberRepository postYoutuberRepository;
//
//    public Post createPost(Post post) {
//        return postRepository.save(post);
//    }
//
//    public Post getPostById(Long postId) {
//        return postRepository.findById(postId).orElse(null);
//    }
//
//    public Post updatePost(Long postId, Post updatedPost) {
//        Post existingPost = postRepository.findById(postId).orElse(null);
//        if (existingPost != null) {
//            existingPost.setTitle(updatedPost.getTitle());
//            // Update other attributes as needed
//            return postRepository.save(existingPost);
//        }
//        return null;
//    }
//
//    public boolean deletePost(Long postId) {
//        Post post = postRepository.findById(postId).orElse(null);
//        if (post != null) {
//            postRepository.delete(post);
//            return true;
//        }
//        return false;
//    }
//    
//    public PostYoutuber createPostYoutuber(PostYoutuberDTO postYoutuberDTO) {
//        Post post = postRepository.findById(postYoutuberDTO.getPostId()).orElse(null);
//        Youtuber youtuber = youtuberRepository.findById(postYoutuberDTO.getYoutuberId()).orElse(null);
//        if (post != null && youtuber != null) {
//            PostYoutuber postYoutuber = new PostYoutuber();
//            postYoutuber.setPost(post);
//            postYoutuber.setYouTuber(youtuber);
//            return postYoutuberRepository.save(postYoutuber);
//        }
//        return null;
//    }
//    
//    
//}
//
