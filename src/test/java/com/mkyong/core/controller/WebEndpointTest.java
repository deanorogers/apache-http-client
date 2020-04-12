package com.mkyong.core.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class WebEndpointTest {

   @Test
   public void getHello() throws IOException {

     CloseableHttpClient httpClient = HttpClients.createDefault();

     String result = "";
     try {

         HttpGet httpGet = new HttpGet("http://localhost:8080");
      
         // add request headers
         httpGet.addHeader("custom-key", "mkyong");
         httpGet.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

         CloseableHttpResponse response = httpClient.execute(httpGet);

         try {

             // Get HttpResponse Status
             System.out.println(response.getProtocolVersion());              // HTTP/1.1
             System.out.println(response.getStatusLine().getStatusCode());   // 200
             System.out.println(response.getStatusLine().getReasonPhrase()); // OK
             System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK


             HttpEntity entity = response.getEntity();
             if (entity != null) {
                 // return it as a String
                 result = EntityUtils.toString(entity);
                 System.out.println("Response [" + result + "]");
             }

           } finally {
             response.close();
           }
        } finally {
          httpClient.close();
        }
        assertEquals(result, "Hello Controller");

    }

    @Test
    public void getHelloWithTimeout() throws IOException {

      CloseableHttpClient httpClient = HttpClients.createDefault();

      String result = "";
      try {

        HttpGet request = new HttpGet("http://localhost:8080");

        RequestConfig requestConfig = RequestConfig.custom()
                        .setSocketTimeout(5000)
                        //.setConnectTimeout(5000)
                        //.setConnectionRequestTimeout(5000)
                        //.setProxy(new HttpHost("myotherproxy", 8080))
                        .build();
          request.setConfig(requestConfig);

          // add request headers
          request.addHeader("custom-key", "mkyong");
          request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

          CloseableHttpResponse response = httpClient.execute(request);

          try {

              // Get HttpResponse Status
              System.out.println(response.getProtocolVersion());              // HTTP/1.1
              System.out.println(response.getStatusLine().getStatusCode());   // 200
              System.out.println(response.getStatusLine().getReasonPhrase()); // OK
              System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK


              HttpEntity entity = response.getEntity();
              if (entity != null) {
                  // return it as a String
                  result = EntityUtils.toString(entity);
                  System.out.println("Response [" + result + "]");
              }

            } finally {
              response.close();
            }
          } catch ( SocketTimeoutException ex) {
            System.out.println("Exception caught [" + ex.getMessage() + "]"  + ex.getClass().getName());
            assertTrue(true, "Socket has timed out as expected");
            return;
          } finally {
           httpClient.close();
         } 
         assertFalse(true, "Should have timed out");

    }

}
