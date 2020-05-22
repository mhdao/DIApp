package com.cst.di.bean;

import com.cst.di.inject.Inject;
import com.cst.di.service.PlaneService;

public class MethodInjectSingleton {
	
	private PlaneService planeService;
	
	public PlaneService getPlaneService() {
		return planeService;
	}
	
	@Inject
	public void setPlaneService(PlaneService planeService) {
		this.planeService = planeService;
	}

	public MethodInjectSingleton() {
	}

	public void fly() {
		planeService.fly();
	}
}
