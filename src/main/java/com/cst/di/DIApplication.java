package com.cst.di;

import com.cst.di.bean.CarServiceSingleton;
import com.cst.di.bean.FieldInjectPrototype;
import com.cst.di.bean.FieldInjectSingleton;
import com.cst.di.bean.MethodInjectPrototype;
import com.cst.di.bean.MethodInjectSingleton;
import com.cst.di.bean.PlaneServicePrototype;
import com.cst.di.inject.InjectHandler;
import com.cst.di.mapper.ServiceMapper;


public class DIApplication {

    public static void main(String[] args) throws Exception {
        InjectHandler injectHandler = InjectHandler.getInstance();
        injectHandler.addMapper(new ServiceMapper());
        
        // check the singleton instance, should be the same for 2 time
        System.out.println("Singleton instance for CarServiceImpl:");
        CarServiceSingleton singletonCar = (CarServiceSingleton) injectHandler.injectInstanceByContructor(CarServiceSingleton.class);
        singletonCar.run();
        singletonCar = (CarServiceSingleton) injectHandler.injectInstanceByContructor(CarServiceSingleton.class);
        singletonCar.run();
        
        // check the prototype instance, should be different for each  time
        System.out.println("Prototype instances for PlaneServiceImpl:");
        PlaneServicePrototype prototypePlane = (PlaneServicePrototype) injectHandler.injectInstanceByContructor(PlaneServicePrototype.class);
        prototypePlane.fly();
        prototypePlane = (PlaneServicePrototype) injectHandler.injectInstanceByContructor(PlaneServicePrototype.class);
        prototypePlane.fly();     
        
        FieldInjectPrototype prototypePlane2 = (FieldInjectPrototype) injectHandler.injectInstanceByField(FieldInjectPrototype.class);
        prototypePlane2.fly();
        
        prototypePlane2 = (FieldInjectPrototype) injectHandler.injectInstanceByField(FieldInjectPrototype.class);
        prototypePlane2.fly();
        
        MethodInjectPrototype prototypePlane3 = (MethodInjectPrototype) injectHandler.injectInstanceByMethod(MethodInjectPrototype.class);
        prototypePlane3.fly();
        
        prototypePlane3 = (MethodInjectPrototype) injectHandler.injectInstanceByMethod(MethodInjectPrototype.class);
        prototypePlane3.fly();
        
        System.out.println("Singleton with Method and Field injection instances for PlaneServiceImpl:");
        
        FieldInjectSingleton prototypePlane4 = (FieldInjectSingleton) injectHandler.injectInstanceByField(FieldInjectSingleton.class);
        prototypePlane4.fly();
        
        prototypePlane4 = (FieldInjectSingleton) injectHandler.injectInstanceByField(FieldInjectSingleton.class);
        prototypePlane4.fly();
        
        MethodInjectSingleton prototypePlane5 = (MethodInjectSingleton) injectHandler.injectInstanceByMethod(MethodInjectSingleton.class);
        prototypePlane5.fly();
        
        prototypePlane5 = (MethodInjectSingleton) injectHandler.injectInstanceByMethod(MethodInjectSingleton.class);
        prototypePlane5.fly();
    }
}
