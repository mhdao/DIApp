package com.cst.di.mapping;

import com.cst.di.mapper.Mapper;
import com.cst.di.service.CarService;
import com.cst.di.service.CarServiceImpl;
import com.cst.di.service.PlaneService;
import com.cst.di.service.PlaneServiceImpl;

public class ServiceMapperTest extends Mapper {

    @Override
    public void configure() {
        createMapping(CarService.class, CarServiceImpl.class);
        createMapping(PlaneService.class, PlaneServiceImpl.class);
    }

}
