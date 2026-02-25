package com.example.handlers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.example.service.Profiler;

public class ProfilingHandler implements InvocationHandler {
	private final Object target;
	private final Class<?> targetClass;
	private final Map<Method, ProfileConfig> profileCache = new ConcurrentHashMap<>();

	public ProfilingHandler(Object target) {
		this.target = target;
		this.targetClass = target.getClass();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		ProfileConfig config = profileCache.computeIfAbsent(method, this::resolveConfig);
		if (config.isProfiled()) {
			long start = System.nanoTime();
			try {
				return method.invoke(target, args);
			} finally {
				long duration = System.nanoTime() - start;
				long convertedDuration = config.timeUnit().convert(duration, TimeUnit.NANOSECONDS);
				System.err.printf("%s runs %d %s.%n", method.getName(), convertedDuration,
						config.timeUnit().name().toLowerCase());
			}
		}
		return method.invoke(target, args);
	}

	private ProfileConfig resolveConfig(Method method) {
		try {
			Method targetMethod = targetClass.getMethod(method.getName(), method.getParameterTypes());

			if (targetMethod.isAnnotationPresent(Profiler.class)) {
				return new ProfileConfig(true, targetMethod.getAnnotation(Profiler.class).value());
			} else if (targetClass.isAnnotationPresent(Profiler.class)) {
				return new ProfileConfig(true, targetClass.getAnnotation(Profiler.class).value());
			}
		} catch (NoSuchMethodException e) {

		}
		return new ProfileConfig(false, TimeUnit.NANOSECONDS);
	}

	private record ProfileConfig(boolean isProfiled, TimeUnit timeUnit) {
	}
}
