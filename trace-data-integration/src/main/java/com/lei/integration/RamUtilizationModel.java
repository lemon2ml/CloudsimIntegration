package com.lei.integration;

import java.io.IOException;

import org.cloudbus.cloudsim.UtilizationModel;

public class RamUtilizationModel extends UtilizationModelTraceData{

	public RamUtilizationModel(String inputPath, double schedulingInterval) throws NumberFormatException, IOException {
		super(inputPath, schedulingInterval);		
	}

	public double getUtilization(double time) {
		if (time % getSchedulingInterval() == 0) {
			return ramdata[(int) time / (int) getSchedulingInterval()];
		}
		int time1 = (int) Math.floor(time / getSchedulingInterval());
		int time2 = (int) Math.ceil(time / getSchedulingInterval());
		double utilization1 = ramdata[time1];
		double utilization2 = ramdata[time2];
		double delta = (utilization2 - utilization1) / ((time2 - time1) * getSchedulingInterval());
		double utilization = utilization1 + delta * (time - time1 * getSchedulingInterval());
		return utilization;

}

}
