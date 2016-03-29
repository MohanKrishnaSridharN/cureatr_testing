package com.cureatr.testpackage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class APIPOSTFINAL {

private final String USER_AGENT = "Mozilla/5.0";
public static void main(String[] args) throws Exception {
	 APIPOSTFINAL http = new APIPOSTFINAL();	
	 System.out.println("CREATE USER: Https POST Request");
	 http.sendPost();
}
 // HTTPS POST request
 private void sendPost() throws Exception {
	 String EMAIL="mohan.nimmala@cureatr.com";
	  String url = "https://cureatr-vm.dev:5001/mtuity/create_user";
	  URL obj = new URL(url);
	  HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	
	  con.setRequestMethod("POST");
	  con.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
	  con.setRequestProperty("institution", "cureatr");
	  con.setRequestProperty("email", EMAIL);
	  con.setRequestProperty("first_name", "t");
	  con.setRequestProperty("last_name", "m");
	  con.setRequestProperty("type", "web");
	  con.setRequestProperty("specialty", "Cardiology");
	  con.setRequestProperty("title", "Resident");
	  con.setRequestProperty("password", "test1234");
	    
	  String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
	  // Send post request
	  con.setDoOutput(true);
	  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	  wr.writeBytes(urlParameters);
	  wr.flush();
	  wr.close();
	  //int responseCode = con.g
	  int responseCode = con.getResponseCode();
	  String response1=con.getResponseMessage();
	  System.out.println(responseCode);
	  System.out.println(response1);
   }
}