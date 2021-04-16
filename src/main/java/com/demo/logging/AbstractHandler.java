package com.demo.logging;

import org.springframework.cglib.proxy.InvocationHandler;

public abstract class AbstractHandler implements InvocationHandler {

	private Object targetObject;

	public Object getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}

}
