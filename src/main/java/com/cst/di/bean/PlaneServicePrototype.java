package com.cst.di.bean;

import com.cst.di.inject.Inject;
import com.cst.di.scope.BeanScope;
import com.cst.di.service.PlaneService;

public class PlaneServicePrototype {

    private PlaneService planeService;

    @Inject(value = BeanScope.PROTOTYPE)
    public PlaneServicePrototype(PlaneService planeService) {
        this.planeService = planeService;
    }

    public void fly() {
        planeService.fly();
    }
}
