package com.cureatr.testpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class pythonscriptcall {

	public static void main(String[] args) {
		    String s = null;
		    try {
		        String[]callAndArgs= {"/usr/bin/pythonw","/Users/macmini/Downloads/pythonstring.py","arg1","arg2"};
		        Process p = Runtime.getRuntime().exec(callAndArgs); 
		        BufferedReader stdInput = new BufferedReader(new 
		             InputStreamReader(p.getInputStream()));
		        BufferedReader stdError = new BufferedReader(new 
		             InputStreamReader(p.getErrorStream()));
		        // read the output
		        while ((s = stdInput.readLine()) != null) {
		            System.out.println(s);
		        }
		        // read any errors
		        while ((s = stdError.readLine()) != null) {
		            System.out.println(s);
		        }
		        System.exit(0);
		    }
		    catch (IOException e) {
		        System.out.println("exception occured");
		        e.printStackTrace();
		        System.exit(-1);
	    }
	}
}
