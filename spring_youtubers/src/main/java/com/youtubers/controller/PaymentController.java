package com.youtubers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youtubers.Service.PaymentService;
import com.youtubers.dto.KakaoApproveResponse;
import com.youtubers.dto.KakaoReadyResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;
	
//    @PostMapping("/payment")
//    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDTO paymentDTO) {
//        Payment createdPayment = paymentService.savePayment(paymentDTO);
//        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
//    }
    
    @GetMapping("/ready")
    public String readyToKakaoPay() {

        return paymentService.kakaoPayReady();
    }
    
    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken) {

        KakaoApproveResponse kakaoApprove = paymentService.ApproveResponse(pgToken);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }
    
    @GetMapping("/cancel")
    public String cancel() {
    	
//    	throw new BusinessLogicException(ExceptionCode.PAY_CANCEL);
    	return "취소했습니다";
    	
    }

    @GetMapping("/fail")
    public String fail() {
    	
//    	throw new BusinessLogicException(ExceptionCode.PAY_FAILED);
    	return "실패했습니다";
    }
}