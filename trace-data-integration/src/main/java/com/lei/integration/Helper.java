package com.lei.integration;

import java.util.ArrayList;
import java.util.List;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletScheduler;
import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelNull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.power.PowerVm;

public class Helper {

	static List<Cloudlet> cloudletList = new ArrayList<Cloudlet>();
	static List<Vm> vmList = new ArrayList<Vm>();

	public static List<Cloudlet> createCloudletList(int brokerId, int number, int[] cpuField, int[] ramField) {
		
		UtilizationModel utilizationModelNull = new UtilizationModelNull();		
		int cnt = 0;
		while(cnt < number) {
			Cloudlet cloudlet = new Cloudlet(
					cnt,
					cpuField[cnt] * Constants.cloudletLength,
					Constants.CLOUDLET_PES,
					Constants.cloudletFileSize,
					Constants.cloudletFileSize,
					new CpuUtilizationModel(cpuField[cnt]),
					new RamUtilizationModel(cpuField[cnt]), utilizationModelNull);
			
			cloudlet.setUserId(brokerId);
			cloudlet.setVmId(cnt);
			cloudletList.add(cloudlet);
			cnt++;
		}
		
		return cloudletList;
	}
	
	public static List<Vm> createVmList(int brokerId, int number){
		int mips = 1000;
		long size = 10000; // image size (MB)
		int ram = 512; // vm memory (MB)
		long bw = 1000;
		int pesNumber = 1; // number of cpus
		String vmm = "Xen"; // VMM name

		for(int i=0; i<number; i++) {
			PowerVm vm = new PowerVm(i, brokerId, mips, pesNumber, 
					ram, bw, size, 1, vmm,
					new CloudletSchedulerTimeShared(), 10.0);
			
			
			vmList.add(vm);
		}
		return vmList;
	}
}
