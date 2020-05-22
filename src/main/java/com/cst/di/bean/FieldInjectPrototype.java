package com.cst.di.bean;

import com.cst.di.inject.Inject;
import com.cst.di.scope.BeanScope;
import com.cst.di.service.PlaneService;

public class FieldInjectPrototype {

	@Inject(value = BeanScope.PROTOTYPE)
	private PlaneService planeService;

	public FieldInjectPrototype() {
	}

	public void fly() {
		planeService.fly();
	}
}
