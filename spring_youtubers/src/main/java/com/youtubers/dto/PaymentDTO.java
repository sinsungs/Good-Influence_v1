package com.youtubers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	
//    private Long payid;
    private Long post_id;
    private Long user_id;
    
}