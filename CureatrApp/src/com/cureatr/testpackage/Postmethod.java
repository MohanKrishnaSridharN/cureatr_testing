package com.cureatr.testpackage;

import java.awt.List;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.gargoylesoftware.htmlunit.util.NameValuePair;

import mx4j.tools.adaptor.http.HttpException;

public class Snippet {
	public static void main(String[] args) throws Exception {
        
	String url = "https://cureatr-vm.dev:5001/mtuity/create_user";
	 
	        InputStream inputStream = null;
	        String result = "";{
	        try {
	
	
	            //HttpGet httpGet = new HttpGet(url);
	            //HttpPost httpPost = new HttpPost(url);
	            //HttpsURLConnection con = SSLExceptionUtility.getNewHttpClient().
	            //final HttpResponse hr = SSLExceptionUtility.getNewHttpClient().execute(httpPost);
	        	
	        	String rawData = "id=10";
	        	String type = "application/x-www-form-urlencoded";
	        	String encodedData = URLEncoder.encode( rawData ); 
	        	
	        	//HttpClient httpclient = HttpClients.createDefault();
	        	//HttpPost httppost = new HttpPost("http://www.a-domain.com/foo/");

	        	// Request parameters and other properties.
	        	HttpPost httpPost = new HttpPost(url);
	            final HttpResponse hr = SSLExceptionUtility.getNewHttpClient().execute(httpPost);
	        	
	        	
	        	
	            con.setRequestProperty("institution", "cureatr");
	            con.setRequestProperty("email", "TestMtuity@cureatr.com");
	            con.setRequestProperty("password", "test1234");
	            con.setRequestProperty("first_name", "test01");
	            con.setRequestProperty("last_name", "mtuity01");
	            con.setRequestProperty("type", "web");
	            con.setRequestProperty("specialty", "Cardiology");
	            con.setRequestProperty("title", "Resident");
	            //final HttpsURLConnection hr = 
	            //Log.i(ActionBarDrawerToggle.Delegate.class.getSimpleName(), "getResponse url == '" + url + "'");
	            //OutputStream os = con.getOutputStream();
	            //os.write(encodedData.getBytes());
	            /*
	            System.out.println("********Response"+hr.getStatusLine());
	            String urlParameters = "https://cureatr-vm.dev:5001/mtuity/create_user";
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(urlParameters);
	            wr.flush();
	            wr.close();
	            */
	            System.out.println("********Response"+((HttpResponse) con).getStatusLine());
	            if (((HttpResponse) con).getStatusLine().getStatusCode() != 200) {
	                throw new HttpException(0, "Server did not return a status of 200, returned " + ((HttpResponse) con).getStatusLine().getStatusCode());
	            }
	
	            //Logger.info("Policy URLs get:", url);
	
	            // convert inputstream to string
	            if (inputStream != null) {
	            	result = "Did work!";
	            	System.out.println("result");
	                //result = convertInputStreamToString(inputStream);
	            } else {
	                result = "Did not work!";
	                System.out.println("result");
	            }
	
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	e.getLocalizedMessage();
	            //Log.d("InputStream", e.getLocalizedMessage());
	        }
	
	       
}
	       
}
}

