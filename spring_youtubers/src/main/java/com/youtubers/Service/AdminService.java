package com.youtubers.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.youtubers.dto.InfluencerDTO;
import com.youtubers.dto.UserDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.entity.User;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminService {
	
	private final UserRepository userRepository;
	
    public List<UserDTO> getAllUsers() {
    	
    	List<User> users = userRepository.findAll();
    	
        List<UserDTO> dtoList = new ArrayList<>();
        
	     for (User user : users) {
	    	 
		      dtoList.add(entityToDTO(user));
		      
		 }
        
	     return dtoList;
        
    }
    
    public void deleteUserById(Long userId) {
    	
        userRepository.deleteById(userId);
        
    }
    
	public void updateUserById(Long userId) {

		 User user = userRepository.findById(userId).orElse(null);
		 user.setSns("인플루언서");
		 
		 userRepository.save(user); 
		
	}



    UserDTO entityToDTO(User user) {
		
    	UserDTO userDTO = UserDTO.builder()
    			
    			.id(user.getId())
	            .email(user.getEmail())
	            .password(user.getPassword())
	            .username(user.getUsername())
	            .regdate(user.getRegDate())
	            .sns(user.getSns())
				.build();
		
		return userDTO;
		
	}



}
