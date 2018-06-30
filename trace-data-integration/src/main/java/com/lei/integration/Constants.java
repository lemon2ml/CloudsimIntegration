package com.lei.integration;

public class Constants {

	/*
	 * cloudlet relevant
	 */
	//任务个数
	public static final int NUMBER_OF_CLOUDLETS = 1052;
	//任务长度，单位：MI
	public static final long CLOUDLET_LENGTH = 10000;
	//处理单元个数
	public final static int CLOUDLET_PES	= 1;
	//输入文件大小，单位：byte
	public static final long CLOUDLET_FILE_SIZE =300;
	//输出文件大小，单位：byte
	public static final long CLOUDLET_OUTPUT_FILE_SIZE =300;
	
	/*
	 * workload relevant
	 */
	//Trace Data
	public static final String TRACE_DATA_FILE = "D:\\TraceData\\task_usage part-00000-of-00500.csv";
	//workload base dir
	public static final String WORKLOAD_DIR = "D:\\Projects\\cloudsim-master\\cloudsim-master\\modules\\cloudsim-examples\\src\\main\\resources\\workload";
	//workload name
	public static final String WORKLOAD_NAME = "GOOGLE_TRACE";
	//lines of per cloudlet file
	public static final int CLOUDLET_INTERNALS = 288;
	
	
	
	
	
	
	
	
	
	
	
	
}
