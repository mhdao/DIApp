package com.cst.di.bean;

import com.cst.di.inject.Inject;
import com.cst.di.scope.BeanScope;
import com.cst.di.service.PlaneService;

public class MethodInjectPrototype {
	
	private PlaneService planeService;
	
	public PlaneService getPlaneService() {
		return planeService;
	}
	
	@Inject(value = BeanScope.PROTOTYPE)
	public void setPlaneService(PlaneService planeService) {
		this.planeService = planeService;
	}

	public MethodInjectPrototype() {
	}

	public void fly() {
		planeService.fly();
	}
}
