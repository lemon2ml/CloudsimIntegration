package com.lei.integration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.cloudbus.cloudsim.UtilizationModel;

/**
 * Defines the resource utilization model based on a
 * <a href="https://www.planet-lab.org">PlanetLab</a> datacenter trace file.
 */
public class UtilizationModelTraceData implements UtilizationModel {

	/** The scheduling interval. */
	private double schedulingInterval;

	/** The data (5 min * 288 = 24 hours). */
	protected final double[] cpudata;
	protected final double[] ramdata;

	/**
	 * Instantiates a new PlanetLab resource utilization model from a trace file.
	 * 
	 * @param inputPath
	 *            The path of a PlanetLab datacenter trace.
	 * @param schedulingInterval
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public UtilizationModelTraceData(String inputPath, double schedulingInterval)
			throws NumberFormatException, IOException {
		cpudata = new double[289];
		ramdata = new double[289];
		setSchedulingInterval(schedulingInterval);
		BufferedReader input = new BufferedReader(new FileReader(inputPath));
		int n = cpudata.length;
		for (int i = 0; i < n - 1; i++) {
			String[] strs = input.readLine().split(",");
			cpudata[i] = Double.valueOf(strs[0]);
			ramdata[i] = Double.valueOf(strs[1]);
		}
		cpudata[n - 1] = cpudata[n - 2];
		ramdata[n - 1] = ramdata[n - 2];
		input.close();
	}

	/**
	 * Instantiates a new PlanetLab resource utilization model with variable data
	 * samples from a trace file.
	 * 
	 * @param inputPath
	 *            The path of a PlanetLab datacenter trace.
	 * @param dataSamples
	 *            number of samples in the file
	 * @throws NumberFormatException
	 *             the number format exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public UtilizationModelTraceData(String inputPath, double schedulingInterval, int dataSamples)
			throws NumberFormatException, IOException {
		setSchedulingInterval(schedulingInterval);
		cpudata = new double[dataSamples];
		ramdata = new double[dataSamples];
		BufferedReader input = new BufferedReader(new FileReader(inputPath));
		int n = cpudata.length;
		for (int i = 0; i < n - 1; i++) {
			cpudata[i] = Integer.valueOf(input.readLine().split(",")[0]) / 100.0;
			ramdata[i] = Integer.valueOf(input.readLine().split(",")[1]) / 100.0;
		}
		cpudata[n - 1] = cpudata[n - 2];
		ramdata[n - 1] = ramdata[n - 2];
		input.close();
	}

	/**
	 * Sets the scheduling interval.
	 * 
	 * @param schedulingInterval
	 *            the new scheduling interval
	 */
	public void setSchedulingInterval(double schedulingInterval) {
		this.schedulingInterval = schedulingInterval;
	}

	/**
	 * Gets the scheduling interval.
	 * 
	 * @return the scheduling interval
	 */
	public double getSchedulingInterval() {
		return schedulingInterval;
	}

	public double[] getData() {
		return cpudata;
	}

	public double getUtilization(double time) {
		if (time % getSchedulingInterval() == 0) {
			return cpudata[(int) time / (int) getSchedulingInterval()];
		}
		int time1 = (int) Math.floor(time / getSchedulingInterval());
		int time2 = (int) Math.ceil(time / getSchedulingInterval());
		double utilization1 = cpudata[time1];
		double utilization2 = cpudata[time2];
		double delta = (utilization2 - utilization1) / ((time2 - time1) * getSchedulingInterval());
		double utilization = utilization1 + delta * (time - time1 * getSchedulingInterval());
		return utilization;

	}
}
