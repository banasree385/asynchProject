package com.ban.asynchProject.commom;

import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class BasicAuthInterceptor implements ClientHttpRequestInterceptor{
    private String username;
    private String password;

    public BasicAuthInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }
	@Override
	public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution)
			throws IOException {
		// TODO Auto-generated method stub
		  HttpHeaders headers = httpRequest.getHeaders();
	        headers.add("Authorization", encodeCredentialsForBasicAuth(username, password));

	        return clientHttpRequestExecution.execute(httpRequest, bytes);
	}
	
	  public static String encodeCredentialsForBasicAuth(String username, String password) {
	        return "Basic " + new Base64().encodeToString((username + ":" + password).getBytes());
	    }
	

}
