package com.demo.logging;

import java.lang.reflect.Method;

public class AfterHandlerImpl extends AfterHandler {

	@Override
	public void handleAfter(Object object, Method method, Object[] args) {
		System.out.println("Handling before actual method execution");
		System.out.println("------");
		
	}

}
