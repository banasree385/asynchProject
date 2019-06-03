package com.ban.asynchProject.commom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * This class is used to inject the basic authentication credentials needed by
 * the backend smart REST api's into each call.
 * 
 * @author <a href="mailto:tim.fassett@ge.com">Tim Fassett (212399319)</a>
 * @version 1.0
 * 
 */
public class SmartRestTemplate extends RestTemplate {

	private String username;
	private String password;


	public SmartRestTemplate(String username, String password){
	        this.username = username;
	        this.password = password;
	        if(username != null)
	        {
	        addAuthentication(username,password);
	        }
	    }

	private void addAuthentication(String username, String password) {

		/*List<ClientHttpRequestInterceptor> interceptors = Collections
				.<ClientHttpRequestInterceptor>singletonList(new BasicAuthorizationInterceptor(username, password));*/
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new BasicAuthInterceptor(username, password));
		setRequestFactory(new InterceptingClientHttpRequestFactory(getRequestFactory(), interceptors));
	}


}
