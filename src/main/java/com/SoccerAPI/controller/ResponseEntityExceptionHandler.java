package com.SoccerAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.SoccerAPI.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ResponseEntityExceptionHandler {

	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Resource Not Found")
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<Object> handle(ResourceNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
		
}

