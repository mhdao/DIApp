package com.cst.di.inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cst.di.bean.CarServiceSingleton;
import com.cst.di.mapping.ServiceMapperTest;
import com.cst.di.service.CarService;

public class SingletonBeanTest {

	private ServiceMapperTest mapperTest = new ServiceMapperTest();;
	private InjectHandler injectHandler = InjectHandler.getInstance();;

	@Test
	public void testSingletonBean_should_getSameInstance() throws Exception {
		injectHandler.addMapper(mapperTest);
		injectHandler.injectInstanceByContructor(CarServiceSingleton.class);
		Object carService1 = injectHandler.getInjectionInstance(CarService.class);

		injectHandler.injectInstanceByContructor(CarServiceSingleton.class);
		Object carService2 = injectHandler.getInjectionInstance(CarService.class);

		Assertions.assertEquals(carService1, carService2);
	}

}
