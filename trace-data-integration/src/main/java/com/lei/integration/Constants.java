package com.lei.integration;

public class Constants {

	//任务个数
	public static final int NUMBER_OF_CLOUDLETS = 50;
	//任务长度，单位：MI
	public static final long cloudletLength = 10000;
	//处理单元个数
	public final static int CLOUDLET_PES	= 1;
	//输入文件大小，单位：byte
	public static final long cloudletFileSize =300;
	//输出文件大小，单位：byte
	public static final long cloudletOutFileSize =300;
	//Trace Data
	public static final String TraceDataFile = "D:\\TraceData\\task_usage part-00000-of-00500.csv";
}
