package com.demo.logging;

import java.lang.reflect.Method;

public abstract class AfterHandler extends AbstractHandler {

	
	public abstract void handleAfter(Object object, Method method, Object[] args);
	
	@Override
	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(method.getDefaultValue(),args);
		handleAfter(object, method, args);
		return result;
	}
}
