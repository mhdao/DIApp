package com.cst.di.inject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.cst.di.mapper.IMapper;
import com.cst.di.scope.BeanScope;

public class InjectHandler {

	private Map<Class<?>, Object> singletonInstanceMap = new HashMap<>();
	private Map<Class<?>, IMapper> mappers = new HashMap<>();
	private static InjectHandler instance = new InjectHandler();

	private InjectHandler() {
	}

	public void addMapper(IMapper mapper) {
		if (!mappers.containsKey(mapper.getClass())) {
			mapper.configure();
			mappers.put(mapper.getClass(), mapper);
		}
	}

	public static InjectHandler getInstance() {
		return instance;
	}

	public Object getInjectionInstance(Class<?> clazz) {
		IMapper mapper = getMapper(clazz);
		if (mapper != null) {
			Class<?> subClazz = mapper.getMapping(clazz);
			return singletonInstanceMap.get(subClazz);
		}
		return null;
	}

	private IMapper getMapper(Class<?> clazz) {
		for (IMapper mapper : mappers.values()) {
			if (mapper.getMapping(clazz) != null) {
				return mapper;
			}
		}
		throw new IllegalArgumentException("Couldn't find the mapping for : " + clazz);
	}

	public Object injectInstanceByContructor(Class<?> clazz) throws Exception {

		if (clazz != null) {

			for (Constructor<?> constructor : clazz.getConstructors()) {
				if (constructor.isAnnotationPresent(Inject.class)) {
					Inject annotation = (Inject) constructor.getAnnotation(Inject.class);
					BeanScope injectValue = annotation.value();

					Class<?>[] parameterTypes = constructor.getParameterTypes();
					Object[] objArr = new Object[parameterTypes.length];

					int i = 0;

					for (Class<?> c : parameterTypes) {
						Class<?> dependency = getMapper(c).getMapping(c);

						if (c.isAssignableFrom(dependency)) {
							objArr[i] = dependency.getConstructor().newInstance();

							if (injectValue == BeanScope.SINGLETON && singletonInstanceMap.containsKey(dependency)) {
								objArr[i] = singletonInstanceMap.get(dependency);
							}
							singletonInstanceMap.put(dependency, objArr[i]);

							i++;
						}
					}

					Object resObj = clazz.getConstructor(parameterTypes).newInstance(objArr);

					return resObj;
				}
			}
		}

		return null;
	}

	public Object injectInstanceByField(Class<?> clazz) throws Exception {
		if (clazz != null) {
			clazz.getDeclaredMethods();

			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(Inject.class)) {
					Inject annotation = (Inject) field.getAnnotation(Inject.class);
					BeanScope injectValue = annotation.value();

					Class<?> parameterTypes = field.getType();
					Class<?> dependency = getMapper(parameterTypes).getMapping(parameterTypes);
					Constructor<?> constructor = dependency.getConstructor(new Class[] {});

					Object obj = (Object) constructor.newInstance(new Object[] {});
					if (parameterTypes.isAssignableFrom(dependency)) {
						if (injectValue == BeanScope.SINGLETON && singletonInstanceMap.containsKey(parameterTypes)) {
							obj = singletonInstanceMap.get(parameterTypes);
						}
						singletonInstanceMap.put(parameterTypes, obj);
					}

					Object resObj = clazz.getConstructor().newInstance(new Object[] {});
					field.setAccessible(true);
					field.set(resObj, obj);
					return resObj;
				}

			}

		}

		return null;
	}

	public Object injectInstanceByMethod(Class<?> clazz) throws Exception {
		if (clazz != null) {

			for (Method method : clazz.getDeclaredMethods()) {
				if (method.isAnnotationPresent(Inject.class)) {
					Inject annotation = (Inject) method.getAnnotation(Inject.class);
					BeanScope injectValue = annotation.value();

					Class<?>[] parameterTypes = method.getParameterTypes();
					Object[] objArr = new Object[parameterTypes.length];
					int i = 0;
					for (Class<?> c : parameterTypes) {
						Class<?> dependency = getMapper(c).getMapping(c);

						if (c.isAssignableFrom(dependency)) {
							objArr[i] = dependency.getConstructor().newInstance();

							if (injectValue == BeanScope.SINGLETON && singletonInstanceMap.containsKey(dependency)) {
								objArr[i] = singletonInstanceMap.get(dependency);
							}
							singletonInstanceMap.put(dependency, objArr[i]);

							i++;
						}
					}
					Object resObj = clazz.getConstructor().newInstance(new Object[] {});
					method.setAccessible(true);
					method.invoke(resObj, objArr);
					return resObj;
				}

			}

		}

		return null;
	}
}
