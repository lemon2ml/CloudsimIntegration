package com.lei.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class WorkloadGeneration {

	File file;
	BufferedReader br;
	String workloadName;
	FileWriter fw;

	public WorkloadGeneration() {

	}

	public WorkloadGeneration(String filename, String workloadName) throws FileNotFoundException {
		this.file = new File(filename);
		this.br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		this.workloadName = workloadName;
	}

	public void generate() throws IOException {
		File dir = new File(workloadName);
		if (!dir.exists() || !dir.isDirectory()) {
			dir.mkdirs();
		}
		for (int i = 0; i < Constants.NUMBER_OF_CLOUDLETS; i++) {
			String cloudletFile = workloadName + "/" + (i + 1) + ".txt";
			File cloudlet = new File(cloudletFile);
			if (!cloudlet.exists() || !cloudlet.isFile()) {
				cloudlet.createNewFile();
			}
			try {
				fw = new FileWriter(cloudlet);
				
				String readline;
				int count = 0;
				while (count < Constants.CLOUDLET_INTERNALS && br.ready() && (readline = br.readLine()) != null) {
					if(writeLine(readline)) {
						count++;					
					}
				}
			}finally {
				fw.close();
			}
			


		}

	}
	
	public boolean writeLine(String readline) throws IOException {
		String cpustr = readline.split(",")[13];
		String ramstr = readline.split(",")[6];
		double k = Double.valueOf(cpustr);
		double j = Double.valueOf(ramstr);

		if (k == 0 || j == 0) {
			return false;
		}
		fw.write(k + "," + j + "\n");
		return true;
	}

	public static void main(String args[]) throws IOException {
		new WorkloadGeneration(Constants.TRACE_DATA_FILE, 
				Constants.WORKLOAD_DIR + "/" +Constants.WORKLOAD_NAME).generate();
		System.out.println("...........End..........");
	}

}
