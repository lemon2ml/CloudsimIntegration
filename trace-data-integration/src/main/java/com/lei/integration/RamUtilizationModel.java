package com.lei.integration;

import org.cloudbus.cloudsim.UtilizationModel;

public class RamUtilizationModel implements UtilizationModel{
	int rate;
	
	
	public RamUtilizationModel(int rate) {
		super();
		this.rate = rate;
	}


	public double getUtilization(double time) {
		return rate/100.0;
	}


}
