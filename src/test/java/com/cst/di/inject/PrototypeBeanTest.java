package com.cst.di.inject;

import com.cst.di.bean.PlaneServicePrototype;
import com.cst.di.inject.InjectHandler;
import com.cst.di.mapping.ServiceMapperTest;
import com.cst.di.service.PlaneService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrototypeBeanTest {
    private ServiceMapperTest mapperTest;
    private InjectHandler injectHandler;

    @BeforeEach
    public void init() {
        mapperTest = new ServiceMapperTest();
        mapperTest.configure();
        injectHandler = new InjectHandler(mapperTest);
    }

    @Test
    public void testPrototypeBean_should_getMultipleInstances() throws Exception {
        injectHandler.injectInstance(PlaneServicePrototype.class);
        Object planeService1 = injectHandler.getInstance(PlaneService.class);

        injectHandler.injectInstance(PlaneServicePrototype.class);
        Object planeService2 = injectHandler.getInstance(PlaneService.class);

        Assertions.assertNotEquals(planeService1, planeService2);
    }
}
