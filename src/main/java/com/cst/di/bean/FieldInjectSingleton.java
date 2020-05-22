package com.cst.di.bean;

import com.cst.di.inject.Inject;
import com.cst.di.service.PlaneService;

public class FieldInjectSingleton {

	@Inject
	private PlaneService planeService;

	public FieldInjectSingleton() {
	}

	public void fly() {
		planeService.fly();
	}
}
