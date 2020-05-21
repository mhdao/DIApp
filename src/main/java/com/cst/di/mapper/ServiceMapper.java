package com.cst.di.mapper;

import com.cst.di.service.CarService;
import com.cst.di.service.CarServiceImpl;
import com.cst.di.service.PlaneService;
import com.cst.di.service.PlaneServiceImpl;

public class ServiceMapper extends Mapper {

    public void configure() {
        createMapping(CarService.class, CarServiceImpl.class);
        createMapping(PlaneService.class, PlaneServiceImpl.class);
    }

}
