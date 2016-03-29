package com.cureatr.testpackage;

//package com.mtuity.reusable.sslexceptionhandling;

/**
 * Created by Phani on 14/12/15.
 */

//import android.util.Log;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.security.KeyStore;


/**
 * This class will perform all network related calls like post, get and put.
 */
public final class SSLExceptionUtility {
    public static final int FOUR_HUNDRED_FOURTY_THREE = 443;
    public static final int EIGHTY = 80;
    //Constructor for Network Utility
    private SSLExceptionUtility(){

    }

    /**
     * Returns Default HTTP client with socket factories initialised.
     * <p/>
     * to do request
     *
     * @return Default HTTP Client
     */


    @SuppressWarnings("deprecation")
	public static HttpClient getNewHttpClient() {
        try {
        	KeyStore trustStore = KeyStore.SecretKeyEntry(KeyStore.TrustedCertificateEntr
            
        	//KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(),EIGHTY));
            registry.register(new Scheme("https", sf, FOUR_HUNDRED_FOURTY_THREE));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            //Log.e("Exception:", "Error in getNewHttpClient:", e);
            return new DefaultHttpClient();
        }
    }


}


