package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	public static final long serialVersionUID = -3128681006635769411L;

	public RecordNotFoundException(String message) {
		super(message);
	}
}
