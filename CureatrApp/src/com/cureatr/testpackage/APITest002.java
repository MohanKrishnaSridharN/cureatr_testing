package com.cureatr.testpackage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class APITest002 {
    public static void main(String[] args) throws Exception {

 public static String PostSSL(String strBuffer) {

        try {
            //for HttpsURLConnection we need to set this property
            System.setProperty("java.protocol.handler.pkgs",
                    "com.sun.net.ssl.internal.www.protocol");

            String KEYSTORE = "", TRUESTORE = "";
          

           //put your keystore cert directly in Project Directory
           KEYSTORE = "mykeycert.p12";

            //set Keystore password
            final String KEYSTOREPASS = "yourKeyPass";
         
          //put your cacerts directly in Project Directory
            TRUESTORE = "cacerts";

           //set Truestore password
            final String TRUESTOREPASS= "yourTruePass";

            //my server URL
            String httpsURL = "https://yourserver";

            URL url = new URL(httpsURL);

            KeyStore ks = KeyStore.getInstance("pkcs12");
            ks.load(new FileInputStream(KEYSTORE), KEYSTOREPASS.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, KEYSTOREPASS.toCharArray());

            SSLContext sslctx2 = SSLContext.getInstance("SSLv3");
            sslctx2.init(kmf.getKeyManagers(), null, null);

            KeyStore ksTrust = KeyStore.getInstance("JKS");
            ksTrust.load(new FileInputStream(TRUESTORE), TRUESTOREPASS.toCharArray());

            //just tested if my certificate was imported in root Cacert
            java.security.cert.Certificate cert = ksTrust.getCertificate("myCert");

            //TrustManager's decide whether to allow connections.
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ksTrust);

            System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
            System.setProperty("javax.net.ssl.trustStoreType", "jks");
            System.setProperty("javax.net.ssl.keyStore", KEYSTORE);
            System.setProperty("javax.net.ssl.trustStore", TRUESTORE);
            System.setProperty("javax.net.debug", "ssl");
            System.setProperty("javax.net.ssl.keyStorePassword", KEYSTOREPASS);
            System.setProperty("javax.net.ssl.trustStorePassword", TRUESTOREPASS);


            sslctx2.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            byte[] buffer = strBuffer.getBytes("UTF-8");
            //******
            HttpsURLConnection.setDefaultSSLSocketFactory(sslctx2.getSocketFactory());

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            ps.write(buffer, 0, buffer.length);
            ps.close();
            con.connect();

            //get Response after SSL connection
            String line = "";
            String line2 = "";

            int myRes = con.getResponseCode();
            if (myRes == HttpsURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                line = br.readLine();
                line2 = line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

                br.close();
            }
            con.disconnect();

            return;

        } catch (Exception e) {
            return;
        }
 }
 }
    }