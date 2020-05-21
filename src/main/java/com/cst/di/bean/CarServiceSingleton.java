package com.cst.di.bean;

import com.cst.di.inject.Inject;
import com.cst.di.scope.BeanScope;
import com.cst.di.service.CarService;

public class CarServiceSingleton {

    private CarService carService;

    @Inject(value = BeanScope.SINGLETON)
    public CarServiceSingleton(CarService carService) {
        this.carService = carService;
    }

    public void run() {
        carService.run();
    }

}
