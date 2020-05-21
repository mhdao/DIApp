package com.cst.di.inject;

import com.cst.di.mapper.IMapper;
import com.cst.di.scope.BeanScope;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class InjectHandler {

    private Map<Class<?>, Object> singletonInstanceMap = new HashMap<>();
    private IMapper mapper;

    public InjectHandler(IMapper mapper) {
        this.mapper = mapper;
    }

    public Object getInstance(Class<?> clazz) {
		Class<?> subClazz = mapper.getMapping(clazz);
        return singletonInstanceMap.get(subClazz);
    }

    public Object injectInstance(Class<?> clazz) throws Exception {

        if (clazz != null) {

            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.isAnnotationPresent(Inject.class)) {
                    Inject annotation = (Inject) constructor.getAnnotation(Inject.class);
                    BeanScope injectValue = annotation.value();

                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    Object[] objArr = new Object[parameterTypes.length];

                    int i = 0;

                    for (Class<?> c : parameterTypes) {
                        Class<?> dependency = mapper.getMapping(c);

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

}
