package com.cureatr.testpackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import org.apache.xerces.impl.dv.util.Base64;


public class POSTJAVA2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String EMAIL="Test002@cureatr.com";
		String urlParameters  = "param1=a&param2=b&param3=c";
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String request        = "https://cureatr-vm.dev:5001/mtuity/create_user";
		URL    url            = new URL( request );
		
		//String authStr = "levimcdonough+seed1@cureatr.com" + ":" + "test1234";
        //String authEncoded = Base64.encode(authStr.getBytes());
        /*
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		//HttpURLConnection conn= (HttpURLConnection) url.openConnection();     
		
		conn.setDoOutput( true );
		conn.setInstanceFollowRedirects( false );
		conn.setRequestMethod( "POST" );
		//conn.setRequestProperty("Authorization", "Basic " + authEncoded);
        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		conn.setRequestProperty( "charset", "utf-8");
		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		  conn.setRequestProperty("institution", "cureatr");
		  conn.setRequestProperty("email", EMAIL);
		  conn.setRequestProperty("first_name", "t");
		  conn.setRequestProperty("last_name", "m");
		  conn.setRequestProperty("type", "web");
		  conn.setRequestProperty("specialty", "Cardiology");
		  conn.setRequestProperty("title", "Resident");
		  conn.setRequestProperty("password", "test1234");
		
		 
		  
		conn.setUseCaches( false );
		
		*/
		DataOutputStream wr; for(int i=0; i<trades.size(); i++){ con = (HttpURLConnection) obj.openConnection(); con.setRequestMethod("POST"); con.setRequestProperty("Accept-Language", "en-US,en;q=0.8"); con.setRequestProperty("Cookie", cookie); con.setDoOutput(true); wr = new DataOutputStream(con.getOutputStream()); wr.writeBytes("trade=" + trades.get(i) + "&code=" + code); wr.flush(); wr.close(); System.out.println(i); }
		int nothing = conn.getResponseCode();
		String morenothing = conn.getResponseMessage();
		System.out.println(nothing);
		System.out.println(morenothing);
		
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.write( postData );
		
		
		int responseCode = conn.getResponseCode();
		  String response1=conn.getResponseMessage();
		 
		  System.out.println(responseCode);
		  System.out.println(response1);
		  
		  //BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		    //for (int c; (c = in.read()) >= 0;)
	          //  System.out.print((char)c);
		
	}

}
