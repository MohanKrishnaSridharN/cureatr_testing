package com.cureatr.testpackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class terminal2 {

	public static void main(String[] args) {
		        try {
		            /* String array to execute commands */
		            String[] command = new String[3];
		            command[0] = "pwd";
		            command[1] = "ls";
		            /* Command you want to execute */
		            command[2] = "c: && date && ls";

		            /* Create process */
		            Process p = Runtime.getRuntime().exec(command);

		            /* Get OuputStream */
		            //PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(p.getOutputStream())), true);
		            
		            /* Read the output of command prompt */
		            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		            String line = reader.readLine();
		            /* Read upto end of execution */
		            while (line != null) {
		                /* Pass the value to command prompt/user input */
		               // writer.println("08-08-2014");
		                System.out.println(line);
		                line = reader.readLine();
		            }
		            /* The stream obtains data from the standard output stream of the process represented by this Process object. */
		            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		            /* The stream obtains data from the error output stream of the process represented by this Process object. */
		            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		            
		            String Input;
		            while ((Input = stdInput.readLine()) != null) {
		                System.out.println(Input);
		            }            
		            
		            String Error;
		            while ((Error = stdError.readLine()) != null) {
		                System.out.println(Error);
		            }
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

	}
