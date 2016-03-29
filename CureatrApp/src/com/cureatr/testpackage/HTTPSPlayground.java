package com.cureatr.testpackage;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPSPlayground {
    public static void main(String[] args) throws Exception {

        URL url = new URL("https://cureatr-vm.dev:5001/mtuity/create_user");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpURLConnection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());

        String serializedMessage = "{}";
        wr.writeBytes(serializedMessage);
        wr.flush();
        wr.close();

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println(responseCode);
    }
}