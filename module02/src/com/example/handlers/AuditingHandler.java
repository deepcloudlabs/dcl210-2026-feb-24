package com.example.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AuditingHandler implements InvocationHandler {
	private Object target;
	
	public AuditingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var methodName = method.getName();
		System.err.println("%s runs with parameters %s".formatted(methodName ,Arrays.toString(args)));
		var result = method.invoke(target, args);
		System.err.println("%s returns %s".formatted(methodName ,result));		
		return result;
	}

}
