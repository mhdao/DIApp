package com.cst.di.mapping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cst.di.service.CarService;
import com.cst.di.service.CarServiceImpl;
import com.cst.di.service.PlaneService;

public class MapperTest {

    private ServiceMapperTest mapperTest;

    @BeforeEach
    public void init() {
        mapperTest = new ServiceMapperTest();
        mapperTest.configure();
    }

    @Test
    public void should_MappingSubClassToSuperClass() {
        Class<?> clazz = mapperTest.getMapping(CarService.class);
        Assertions.assertTrue(clazz.isInstance(new CarServiceImpl()));
    }

    @Test
    public void shouldThrowExceptionForUnconfiguredClass() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> mapperTest.getMapping(PlaneService.class));
    }

}
