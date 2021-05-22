package com.tdc.salesforce;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.protocol.HTTP;

import org.json.JSONException;
import org.json.JSONObject;


public class SalesForceClient {
    public SalesForceClient() {
        super();
    }
    
    public void getAccessToken(){
        try{
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("https://login.salesforce.com/services/oauth2/token");
         /*   post.addHeader("code","code");
            post.addHeader("grant_type","authorization_code");

            // For session ID instead of OAuth 2.0, use "grant_type", "password" 
            post.addHeader("client_id","3MVG9ZL0ppGP5UrCmzR7P_F8PIHiRMMSRGxsHzDFXAhUDWhlnpPZDPFvBkQV49XdpdTX.jj7jcThWomdAcPiU");
            post.addHeader("client_secret","1978666767171552515");
            post.addHeader("redirect_uri","https://ap1.salesforce.com/services/oauth2/token"); */
            
            List<BasicNameValuePair> parametersBody = new ArrayList<BasicNameValuePair>();
            parametersBody.add(new BasicNameValuePair("grant_type", "authorization_code"));
            parametersBody.add(new BasicNameValuePair("client_id", "3MVG9ZL0ppGP5UrAhG56QYE.w8sJQNHZ.8XRqA7SAHpmvFcZ5APtihac1RGHK_UozJ937e5CKXJN0yLbD_Cc."));
            parametersBody.add(new BasicNameValuePair("client_secret","3229394580101003779"));
            parametersBody.add(new BasicNameValuePair("response_type","code"));
            parametersBody.add(new BasicNameValuePair("redirect_uri","https://localhost:8443/RestTest/outh/_callback"));
        //    parametersBody.add(new BasicNameValuePair("code", "code"));
            post.setEntity(new UrlEncodedFormEntity(parametersBody, HTTP.UTF_8));
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        //    post.setHeader("Code", "code");
            
            HttpResponse response = httpClient.execute(post);
            String accessToken = null;
            JSONObject json = json = new JSONObject(response);
            System.out.println("Json is :: " + json);
            System.out.println("Status code :: " + response.getStatusLine());
            accessToken = json.getString("access_token");
            String issuedAt = json.getString("issued_at");
            System.out.println("Access Token is :: " + accessToken);
            System.out.println("Issued at :: " + issuedAt);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void getToken(){
        String body = "grant_type=password&client_id=3MVG9ZL0ppGP5UrAhG56QYE.w8sJQNHZ.8XRqA7SAHpmvFcZ5APtihac1RGHK_UozJ937e5CKXJN0yLbD_Cc.&client_secret=3229394580101003779&username=surender.kokkonda1255%40gmail.com&password=Passw0rd1";
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("https://login.salesforce.com/services/oauth2/token");
        post.setRequestBody(body);
        post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
        try {
            client.executeMethod(post);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseBody = post.getResponseBodyAsString();
        System.out.println("Response is :: " + responseBody);
        String accessToken = null;
        JSONObject json = null;
        try {
                json = new JSONObject(responseBody); 
                accessToken = json.getString("access_token");
                String issuedAt = json.getString("issued_at");
                System.out.println("Tokne is :: " + accessToken);
                System.out.println("Issued At :: " + issuedAt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }
    public void createSalesForceUser(){
        int statusCode = 0;
        CloseableHttpClient httpClient = null;
        String URI = "https://na1.salesforce.com/services/data/v20.0/sobjects/User";
         try{
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("Username","TestUser1@gmail.com");
            jsonObj.put("LastName","User1");
            jsonObj.put("FirstName","TestUser1@gmail");
            jsonObj.put("Name","TestUser1");
            jsonObj.put("Email","TestUser1@gmail.com");
            jsonObj.put("IsActive","true");
            jsonObj.put("ProfileId","00e28000001MkKfAAK");
            HttpPut objgetPut = new HttpPut(URI);
            objgetPut.addHeader("Authorization" , "Bearer "+ "00D28000001G5qN!AQcAQAuO5Q1FtyCZC9f2FazRYOfPdBUBcG92tVVibCh0DbCsrvOZ.dsVguS5GWPFPTg8yJRtNuP7lZbB7JZ1JPLKHkdDm2.G");
            objgetPut.setEntity(new StringEntity(jsonObj.toString(), ContentType.create("application/json")));
            objgetPut.setHeader("Content-type", "application/json");
            httpClient = HttpClientBuilder.create().build();
            HttpResponse response = httpClient.execute(objgetPut);
            statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status code is :: " + statusCode);
            System.out.println("Response is :: " + response);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        } catch (ClientProtocolException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createUser(){
        String body = "grant_type=password&client_id=3MVG9ZL0ppGP5UrAhG56QYE.w8sJQNHZ.8XRqA7SAHpmvFcZ5APtihac1RGHK_UozJ937e5CKXJN0yLbD_Cc.&client_secret=3229394580101003779&username=surender.kokkonda1255%40gmail.com&password=Passw0rd1";
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("https://na1.salesforce.com/services/data/v20.0/sobjects/User");
        post.setRequestHeader("Authorization" , "Bearer "+ "00D28000001G5qN!AQcAQAuO5Q1FtyCZC9f2FazRYOfPdBUBcG92tVVibCh0DbCsrvOZ.dsVguS5GWPFPTg8yJRtNuP7lZbB7JZ1JPLKHkdDm2.G");
        post.setParameter("Username","TestUser1@gmail.com");
        post.setParameter("LastName","User1");
        post.setParameter("FirstName","TestUser1@gmail");
        post.setParameter("Name","TestUser1");
        post.setParameter("Email","TestUser1@gmail.com");
        post.setParameter("IsActive","true");
        post.setParameter("ProfileId","00e28000001MkKfAAK");
        post.setRequestBody(body);
        post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            client.executeMethod(post);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseBody = post.getResponseBodyAsString();
        System.out.println("Response is :: " + responseBody);
    }
    public static void main(String args[]){
        SalesForceClient client = new SalesForceClient();
       // client.getAccessToken();
       // client.getToken();
       // client.createSalesForceUser();
        client.createUser();
    }
}
