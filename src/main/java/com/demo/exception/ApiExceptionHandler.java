//package com.demo.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//import com.demo.model.ResponseDataModel;
//
//@RestControllerAdvice
//public class ApiExceptionHandler {
//
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public ResponseDataModel handleAllException(Exception ex, WebRequest webRequest) {
//		
//		return new ResponseDataModel(1000, ex.getLocalizedMessage());
//	}
//	
//	@ExceptionHandler(IndexOutOfBoundsException.class)
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	public ResponseDataModel productException(Exception ex, WebRequest webRequest) {
//		
//		return new ResponseDataModel(1000, "k ton tai");
//	}
//}
