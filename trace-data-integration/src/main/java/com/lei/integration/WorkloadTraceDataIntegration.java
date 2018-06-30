package com.lei.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorkloadTraceDataIntegration {
	private final File file;

	private BufferedReader reader;
	
	
	int[] cpuField = new int[Constants.NUMBER_OF_CLOUDLETS];
	int[] ramField = new int[Constants.NUMBER_OF_CLOUDLETS];
	
	public WorkloadTraceDataIntegration() throws FileNotFoundException {
		this.file = new File(Constants.TraceDataFile);
		this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	}
	
	public int[] getCpuUtilizationModel() throws IOException{
		String readline = null;
		int count = cpuField.length;
		int i = 0;
		while(i < count && reader.ready() && (readline = reader.readLine()) != null) {
			String[] Fields = readline.split(",");
			 int k = ((Double)(Double.valueOf(Fields[13]) * 100.0)).intValue();
			 cpuField[i] = k;
			 if(k == 0) {
				 i--;
			 }
			i++;
		}
		return cpuField;
	}
	
	public int[] getRamUtilizationModel() throws IOException{
		String readline = null;
		int count = ramField.length;
		int i = 0;
		while(i < count && reader.ready() && (readline = reader.readLine()) != null) {
			String[] Fields = readline.split(",");
			int k =  ((Double)(Double.valueOf(Fields[6]) * 100.0)).intValue();
			ramField[i] = k;
			 if(k == 0) {
				 i--;
			 }
			 i++;
		}
		return ramField;
	}
	
	public static void main(String[] args) throws IOException {
		WorkloadTraceDataIntegration wtdi = new WorkloadTraceDataIntegration();
		int[] cpus = wtdi.getRamUtilizationModel();
		int cnt = 0;
		
		System.out.println("\n___________________start________________________");
		for(int cpu : cpus) {
			System.out.print(cpu + ", ");
			if(++cnt % 10 == 0) {
				System.out.println();
			}
		}
	}
	
}
