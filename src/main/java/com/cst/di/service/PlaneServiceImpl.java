package com.cst.di.service;

public class PlaneServiceImpl implements PlaneService {
    @Override
    public void fly() {
        System.out.println("The flying Plane is " + this);
    }
}
