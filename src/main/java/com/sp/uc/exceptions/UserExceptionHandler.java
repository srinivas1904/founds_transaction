package com.sp.uc.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class UserExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest req){
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundExcpetion.class)
	public final ResponseEntity<Object> handleUserNFException(UserNotFoundExcpetion ex, WebRequest req){
		ExceptionResponse exceptionResponse=new ExceptionResponse(new Date(),ex.getMessage(), req.getDescription(false));
		
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	//It is not working
	 @Override 
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
	  WebRequest request) { 
		 ExceptionResponse exceptionResp=new 	  ExceptionResponse(new Date(),
				 ex.getMessage(),ex.getBindingResult().toString());
	  
	 return new ResponseEntity(exceptionResp, HttpStatus.BAD_REQUEST); 
	 }
}
