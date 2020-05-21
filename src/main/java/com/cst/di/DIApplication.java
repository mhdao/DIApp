package com.cst.di;

import com.cst.di.bean.CarServiceSingleton;
import com.cst.di.bean.PlaneServicePrototype;
import com.cst.di.inject.InjectFactory;
import com.cst.di.inject.InjectHandler;
import com.cst.di.mapper.ServiceMapper;


public class DIApplication {

    public static void main(String[] args) throws Exception {
        InjectHandler injectHandler = InjectFactory.getInject(new ServiceMapper());

        // check the singleton instance, should be the same for 2 time
        System.out.println("Singleton instance for CarServiceImpl:");
        CarServiceSingleton singletonCar = (CarServiceSingleton) injectHandler.injectInstance(CarServiceSingleton.class);
        singletonCar.run();
        singletonCar = (CarServiceSingleton) injectHandler.injectInstance(CarServiceSingleton.class);
        singletonCar.run();
        
        // check the prototype instance, should be different for each  time
        System.out.println("Prototype instances for PlaneServiceImpl:");
        PlaneServicePrototype prototypePlane = (PlaneServicePrototype) injectHandler.injectInstance(PlaneServicePrototype.class);
        prototypePlane.fly();
        prototypePlane = (PlaneServicePrototype) injectHandler.injectInstance(PlaneServicePrototype.class);
        prototypePlane.fly();        
    }
}
