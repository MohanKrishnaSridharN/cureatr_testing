package com.cureatr.testpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.bouncycastle.util.encoders.Base64;

public class APIPOST001 {

	private static final int HTTP_CONNECT_TIME_OUT = 0;
	private static final int HTTP_READ_TIME_OUT = 0;

	public static void main(String[] args) {
	String dateTime;
	String mobileNum;
	String urlParameters="dateTime=" + URLEncoder.encode(dateTime,"UTF-8")+
		    "&mobileNum="+URLEncoder.encode(mobileNum,"UTF-8");

		URL url = new URL("https://cureatr-vm.dev:5001/mtuity/create_user");
		HttpsURLConnection conn;
		conn=(HttpsURLConnection)url.openConnection();

		// Create the SSL connection
		SSLContext sc;
		sc = SSLContext.getInstance("TLS");
		sc.init(null, null, new java.security.SecureRandom());
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setConnectTimeout(HTTP_CONNECT_TIME_OUT);
		conn.setReadTimeout(HTTP_READ_TIME_OUT);

		//set the output to true, indicating you are outputting(uploading) POST data
		conn.setDoOutput(true);
		//once you set the output to true, you don't really need to set the request method to post, but I'm doing it anyway
		conn.setRequestMethod("POST");
		conn.setFixedLengthStreamingMode(urlParameters.getBytes().length);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		PrintWriter out = new PrintWriter(conn.getOutputStream());
		out.print(urlParameters);
		out.close();

		InputStream is = conn.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
		  String response = inputLine;            
		}  
		// TODO Auto-generated method stub
		InputStream getInputStream(String urlStr, String user, String password) throws IOException
		{
		    URL url = new URL(urlStr);
		    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
		    // Create the SSL connection
		    SSLContext sc;
		    sc = SSLContext.getInstance("TLS");
		    sc.init(null, null, new java.security.SecureRandom());
		    conn.setSSLSocketFactory(sc.getSocketFactory());
		
		    // Use this if you need SSL authentication
		    String userpass = user + ":" + password;
		    String basicAuth = "Basic " + Base64.encodeToString(userpass.getBytes(), Base64.DEFAULT);
		    conn.setRequestProperty("Authorization", basicAuth);
		
		    // set Timeout and method
		    conn.setReadTimeout(7000);
		    conn.setConnectTimeout(7000);
		    conn.setRequestMethod("POST");
		    conn.setDoInput(true);
		
		    // Add any data you wish to post here
		
		    conn.connect();
		    return conn.getInputStream();
		}   
	

	}

}
