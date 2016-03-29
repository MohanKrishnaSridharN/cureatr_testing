package com.cureatr.testpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class terminalcmds {

    public static void main(String[] args) throws IOException, InterruptedException {

/*        String[] command = {"cd /Users/macmini/Music", "ls"};
    	Runtime rt = Runtime.getRuntime();
    	//Process  proc=rt.exec("ping -c 3 www.google.com");
    	Process  proc = null;
        for(int i=0; i<command.length; i++){
    	
        	proc=rt.exec(command[i]);
        	
        }
  */
      String[] command = {"pwd", "ls"};
      Process proc = Runtime.getRuntime().exec(command);
    	
    	//String[] arguments = new String[] {"/Applications/Utilities/Terminal.app", "ssh cureatr@cureatr-vm.dev", "dev; ./tools/admin.py dev", "user_create", "{'first_name': 'test_mtuity_first_name', 'last_name': 'test_mtuity_last_name', 'username': 'test_mtuity_username', 'password': 'testing1234', 'email': 'levi+test_mtuity1@cureatr.com', 'institution': 'cureatr', 'specialty': 'Other', 'title': 'Demo User'}"};
    	//Process proc = new ProcessBuilder(arguments).start();
        
    	//Runtime rt = Runtime.getRuntime();
    	//Process  proc=rt.exec("ping -c 3 www.google.com");
    	// Read the output

        BufferedReader reader =  
                new BufferedReader(new InputStreamReader(proc.getInputStream()));

          String line = "";
          while((line = reader.readLine()) != null) {
              System.out.print(line + "\n");
          }

        proc.waitFor();   

    }
} 
