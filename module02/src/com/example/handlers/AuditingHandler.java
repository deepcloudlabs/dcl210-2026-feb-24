package com.example.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class AuditingHandler implements InvocationHandler {
	private final Object target;

	public AuditingHandler(Object target) {
		this.target = Objects.requireNonNull(target, "Target object cannot be null");
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();

		String params = (args == null) ? "[]" : Arrays.toString(args);

		System.err.printf("[AUDIT] Executing: %s | Parameters: %s%n", methodName, params);

		try {
			Object result = method.invoke(target, args);
			System.err.printf("[AUDIT] Completed: %s | Returns: %s%n", methodName, result);
			return result;

		} catch (Exception e) {
			System.err.printf("[AUDIT] Failed: %s | Exception: %s%n", methodName, e.getCause());
			throw e.getCause() != null ? e.getCause() : e;
		}
	}
}