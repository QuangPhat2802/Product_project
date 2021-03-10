package com.demo.exception;

public class ProductTransactionException extends Exception{

	public static final long  serialVersionUID = -3128681006635769411L;
	
	public ProductTransactionException(String message) {
		super(message);
	}
}
