package com.youtubers.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	
	USERNAME_DUPLICATED(HttpStatus.CONFLICT, ""),
	USERNAME_NOTFOUND(HttpStatus.NOT_FOUND, ""),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "");
	
	private HttpStatus httpStatus;
	private String message;
}
