package com.lei.integration;

import org.cloudbus.cloudsim.power.models.PowerModelSpecPower;

public class CpuRamPowerModel extends PowerModelSpecPower {

	private final double[] ramPower = {
			1, 45, 85
	};
	private final double[] cpuPower = {
			105, 112, 118, 125, 131, 137, 147, 153, 157, 164, 169 
	};
	
	@Override
	public double getPower(double utilization) throws IllegalArgumentException {
		double rampower = 0.0;
		double power = 0.0;
		int utiForRam = (int) utilization;
		if(utilization == -1) {//
			rampower = ramPower[0];
		}else if(utilization == -2) {
			rampower = ramPower[1];
		}else if(utilization == -3) {
			rampower = ramPower[2];
		}else if (utilization < 0 || utilization > 1) {
			throw new IllegalArgumentException("Utilization value must be between 0 and 1");
		}
		else if (utilization % 0.1 == 0) {
			return getPowerData((int) (utilization * 10));
		}else {
			int utilization1 = (int) Math.floor(utilization * 10);
			int utilization2 = (int) Math.ceil(utilization * 10);
			double power1 = getPowerData(utilization1);
			double power2 = getPowerData(utilization2);
			double delta = (power2 - power1) / 10;
			power = power1 + delta * (utilization - (double) utilization1 / 10) * 100;
		}
		return power + rampower;

	}

	@Override
	protected double getPowerData(int index) {
		return cpuPower[index];
	}

}
