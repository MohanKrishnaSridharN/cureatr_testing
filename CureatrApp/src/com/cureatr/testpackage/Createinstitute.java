package com.cureatr.testpackage;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import org.apache.xerces.impl.dv.util.Base64;


public class Createinstitute {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String urlParameters  = "param1=a&param2=b&param3=c";
		String urlParameters  = "parent_iid=&master_iid=&id=postman&short_name=postman corp&name=postman corp name&admin_email=levimcdonough+seed1@cureatr.com";
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String request        = "https://cureatr-vm.dev:5001/mtuity/icreate";
		URL    url            = new URL( request );
		
		//String authStr = "levimcdonough+seed1@cureatr.com" + ":" + "test1234";
        //String authEncoded = Base64.encode(authStr.getBytes());
        
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//HttpURLConnection conn= (HttpURLConnection) url.openConnection();     
		
		conn.setDoOutput( true );
		conn.setInstanceFollowRedirects( false );
		conn.setRequestMethod( "POST" );
		//conn.setRequestProperty("Authorization", "Basic " + authEncoded);
        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		conn.setRequestProperty( "charset", "utf-8");
		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		
		//conn.setRequestProperty("institution", "cureatr");
		//conn.setRequestProperty("email", EMAIL);
		//conn.setRequestProperty("first_name", "test");
		//conn.setRequestProperty("last_name", "mohan");
		//conn.setRequestProperty("type", "web");
		//conn.setRequestProperty("specialty", "Cardiology");
		//conn.setRequestProperty("title", "Resident");
		//conn.setRequestProperty("password", "test1234");
				  
		conn.setUseCaches( false );
		//int nothing = conn.getResponseCode();
		//String morenothing = conn.getResponseMessage();
		//System.out.println(nothing);
		//System.out.println(morenothing);
		
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.write( postData );
		
		int responseCode = conn.getResponseCode();
		String response1=conn.getResponseMessage();
		String response2=conn.getRequestMethod();
		  
		System.out.println(response2);
		System.out.println(responseCode);
		System.out.println(response1);
		  
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		for (int c; (c = in.read()) >= 0;)
	        System.out.print((char)c);
		
	}

}

