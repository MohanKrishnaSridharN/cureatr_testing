package com.cureatr.testpackage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class APITest {

 private final String USER_AGENT = "Mozilla/5.0";
 public static void main(String[] args) throws Exception {
  
	 APITest http = new APITest();
  
	 //System.out.println("Testing 1 - Send Http GET request");
	 //http.sendGet();
  
	  System.out.println("\nTesting 2 - Send Http POST request");
	  http.sendPost();

}

 // HTTP GET request
 private void sendGet() throws Exception {

	  String url = "http://182.72.216.215:8888/Help/Api/GET-api-Generic-aboutus";
	  
	  URL obj = new URL(url);
	  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
	  // optional default is GET
	  con.setRequestMethod("GET");
	  //add request header
	  con.setRequestProperty("User-Agent", USER_AGENT);
	  int responseCode = con.getResponseCode();
	  String responseMsg = con.getResponseMessage();
	  System.out.println("This is Response Msg"+responseMsg);
	  System.out.println("\nSending 'GET' request to URL : " + url);
	  System.out.println("Response Code : " + responseCode);
	
	  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	  String inputLine;
	  StringBuffer response = new StringBuffer();
	
	  while ((inputLine = in.readLine()) != null) {
	   response.append(inputLine);
	  }
	  in.close();
	
	  //print result
	  System.out.println(response.toString());
	}
 
 // HTTP POST request
 private void sendPost() throws Exception {
//testing  sdfsldfjk
  //String url = "https://cureatr-vm.dev:5001/mtuity/create_user";
	 String url= "https://cureatr-vm.dev:5001/mtuity/icreate";
			 //URL obj = new URL(null, "https://cureatr-vm.dev:5001/mtuity/create_user", new sun.net.www.protocol.https.Handler());
  URL obj = new URL(url);
  HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
  //URLConnection con = obj.openConnection();
  //add reuqest header
  
  //((HttpURLConnection) con).setRequestMethod("POST");
  con.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
  con.setRequestProperty("parrent_iid", "");
  con.setRequestProperty("master_iid", "");
  con.setRequestProperty("id", "postman");
  con.setRequestProperty("short_name", "postman corp");
  con.setRequestProperty("name", "postman corp name");
  con.setRequestProperty("admin_email", "levimcdonough+seed1@cureatr.com");
  /*
  con.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
  con.setRequestProperty("institution", "cureatr");
  con.setRequestProperty("email", "TestMtuity@cureatr.com");
  con.setRequestProperty("password", "test1234");
  con.setRequestProperty("first_name", "test01");
  con.setRequestProperty("last_name", "mtuity01");
  con.setRequestProperty("type", "web");
  con.setRequestProperty("specialty", "Cardiology");
  con.setRequestProperty("title", "Resident");
  */
  //con.setRequestProperty("password", "iOS");
  
  
  //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

  //String urlParameters = "https://cureatr-vm.dev:5001/mtuity/create_user";
  String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
  // Send post request
  con.setDoOutput(true);
  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
  
  wr.writeBytes(urlParameters);
  wr.flush();
  wr.close();

  //int responseCode = con.g
  System.out.println("\nSending 'POST' request to URL : " + obj);
  //System.out.println("Post parameters : " + urlParameters);
  //System.out.println("Response Code : " + responseCode);
  int responseCode = con.getResponseCode();
  String response1=con.getResponseMessage();
  //String response2=con.getRequestProperties();
  
  System.out.println(responseCode);
  System.out.println(response1);
  //System.out.println(response2);
  
  BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
  String inputLine;
  StringBuffer response = new StringBuffer();

  while ((inputLine = in.readLine()) != null) {
   response.append(inputLine);
  }
  in.close();
  
  //print result
  System.out.println(response.toString());

 }

}
