package com.cst.di.inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.cst.di.bean.PlaneServicePrototype;
import com.cst.di.mapping.ServiceMapperTest;
import com.cst.di.service.PlaneService;

public class PrototypeBeanTest {
	private ServiceMapperTest mapperTest = new ServiceMapperTest();;
	private InjectHandler injectHandler = InjectHandler.getInstance();;

	@Test
	public void testPrototypeBean_should_getMultipleInstances() throws Exception {
		injectHandler.addMapper(mapperTest);
		injectHandler.injectInstanceByContructor(PlaneServicePrototype.class);
		Object planeService1 = injectHandler.getInjectionInstance(PlaneService.class);

		injectHandler.injectInstanceByContructor(PlaneServicePrototype.class);
		Object planeService2 = injectHandler.getInjectionInstance(PlaneService.class);

		Assertions.assertNotEquals(planeService1, planeService2);
	}
}
