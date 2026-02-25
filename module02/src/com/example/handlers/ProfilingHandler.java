package com.example.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.example.service.Profiler;

public class ProfilingHandler implements InvocationHandler {
	private Object target;
	private Class<?> clazz;
	public ProfilingHandler(Object target) {
		this.target = target;
		this.clazz = target.getClass();
		System.err.println(this.clazz);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var isProfiled = false;
		var timeUnit = TimeUnit.NANOSECONDS;
		var methodName = method.getName();
			
		if (this.clazz.isAnnotationPresent(Profiler.class)) {
			isProfiled = true;
			timeUnit = this.clazz.getAnnotation(Profiler.class).value();
		}
		Method targetObjectMethod = null;
		for (var targetMethod: clazz.getDeclaredMethods()) {
			if (targetMethod.getName() == method.getName()) {
				targetObjectMethod = targetMethod;
				break;
			}
		}
		if (Objects.nonNull(targetObjectMethod) && targetObjectMethod.isAnnotationPresent(Profiler.class)) {
			isProfiled = true;
			timeUnit = targetObjectMethod.getAnnotation(Profiler.class).value();
		}
		if (isProfiled) {
			var start = System.nanoTime();
			var result = method.invoke(target, args);
			var stop = System.nanoTime();
			System.err.println("%s runs %d ns.".formatted(methodName, (stop - start)));
			return result;			
		} 
		return method.invoke(target, args);
	}

}
