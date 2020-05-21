package com.cst.di.inject;

import com.cst.di.bean.CarServiceSingleton;
import com.cst.di.inject.InjectHandler;
import com.cst.di.mapping.ServiceMapperTest;
import com.cst.di.service.CarService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonBeanTest {

    private ServiceMapperTest mapperTest;
    private InjectHandler injectHandler;

    @BeforeEach
    public void init() {
        mapperTest = new ServiceMapperTest();
        mapperTest.configure();
        injectHandler = new InjectHandler(mapperTest);
    }

    @Test
    public void testSingletonBean_should_getSameInstance() throws Exception {
        injectHandler.injectInstance(CarServiceSingleton.class);
        Object carService1 = injectHandler.getInstance(CarService.class);

        injectHandler.injectInstance(CarServiceSingleton.class);
        Object carService2 = injectHandler.getInstance(CarService.class);

        Assertions.assertEquals(carService1, carService2);
    }


}
