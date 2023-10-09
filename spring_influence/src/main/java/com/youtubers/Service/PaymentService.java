package com.youtubers.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.youtubers.dto.KakaoApproveResponse;
import com.youtubers.dto.KakaoReadyResponse;
import com.youtubers.dto.PaymentDTO;
import com.youtubers.entity.Influencer;
import com.youtubers.entity.Payment;
import com.youtubers.entity.Post;
import com.youtubers.entity.User;
import com.youtubers.repository.PaymentRepository;
import com.youtubers.repository.PostRepository;
import com.youtubers.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class PaymentService {
	
	private final PaymentRepository paymentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	
	static final String cid = "TC0ONETIME";
	static final String admin_Key = "12b3220cbea8b0c831b8e89749e396ec";
	private KakaoReadyResponse kakaoReady;
	
	
	
    public Payment savePayment(PaymentDTO dto) {
    	
        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
        
        user.setAmount(user.getAmount() + dto.getDeposit() );
        
        User depositUser = userRepository.save(user);
        
        
        Payment payment = new Payment();
//        payment.setPost(post);
        payment.setUser(depositUser);
        payment.setDeposit(dto.getDeposit());
        payment.setPaytype("kakaopay");
        
        return paymentRepository.save(payment);
    }
    
    
    public String kakaoPayReady(Authentication authentication) {
    	
    	// 카카오페이 요청 양식
    	MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", authentication.getName());
        parameters.add("item_name", "상품명");
        parameters.add("quantity", "1");
        parameters.add("total_amount", "33000");
        parameters.add("vat_amount", "3000");
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "https://goodinfluence.store/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "https://goodinfluence.store/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "https://goodinfluence.store/payment/fail"); // 실패 시 redirect url
    
    	// 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
    	
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        
        kakaoReady = restTemplate.postForObject(
        		"https://kapi.kakao.com/v1/payment/ready",
        		requestEntity,
        		KakaoReadyResponse.class);
        
        System.out.println(kakaoReady.getNext_redirect_pc_url());
        
        kakaoReady.setPartner_user_id(authentication.getName());
        
        return kakaoReady.getNext_redirect_pc_url();    	
    	
    }
    
    public KakaoApproveResponse ApproveResponse(String pgToken) {

        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", kakaoReady.getTid());
//        parameters.add("tid", "T1234567890123456789");
        parameters.add("partner_order_id", "가맹점 주문 번호");
        parameters.add("partner_user_id", kakaoReady.getPartner_user_id());
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
        
        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();
        
        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);
        
	      PaymentDTO dto = new PaymentDTO();
	      
	      dto.setDeposit(approveResponse.getAmount().getTotal());
//	      paymentDTO.setEmail("rkdtlstjd123@naver.com");
	  	  dto.setEmail(approveResponse.getPartner_user_id());
	  	  log.info("유저아이디 : "+approveResponse.getPartner_user_id());
	  	  
	      
	      savePayment(dto);
                
        return approveResponse;    	
    	
    }
    
    /** 카카오 요구 헤더값 **/
    
    private HttpHeaders getHeaders() {
    	HttpHeaders httpHeaders = new HttpHeaders();
    	
    	String auth = "KakaoAK " + admin_Key;
    	
    	httpHeaders.set("Authorization", auth);
    	httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    	
    	return httpHeaders;
    }
    
}
