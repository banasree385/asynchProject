package com.ban.asynchProject.service;

import java.util.Arrays;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ban.asynchProject.commom.SmartRestTemplate;
import com.ge.capital.efs.smart_msexcel_beans.response.SmartInputVariableResponseWrapper;
import com.ge.capital.efs.smart_msexcel_beans.response.SmartOutputVariableResponseWrapper;

@Service("testService")
public class TestServiceImpl implements TestService {
	@Value("${services.publish.url}")
	private String smartServiceUrl;
	@Valid
	@NotNull
	@Value("${services.authentication.user}")
	private String authenticationUser;

	@Valid
	@NotNull
	@Value("${services.authentication.password}")
	private String authenticationPassword;
	@Async("asyncExecutor")
	public CompletableFuture<List<String>> call1() throws InterruptedException{
		System.out.println("Execute method asynchronously 1- "
			      + Thread.currentThread().getName());
		
		String[] strar1={"banani","banasree"};
		List<String> list1=Arrays.asList(strar1);
		System.out.println("AsynchServiceImpl :call1");
		Thread.sleep(10000);
		CompletableFuture<List<String>> future= CompletableFuture.completedFuture(list1);
		System.out.println("after future 1");
		return future;
	};
	@Async("asyncExecutor")
	public CompletableFuture<List<String>> call2(){
		System.out.println("Execute method asynchronously 2- "
			      + Thread.currentThread().getName());
		System.out.println("AsynchServiceImpl :call2");
		String[] strar2={"Apu","Vishnu"};
		List<String> list1=Arrays.asList(strar2);
		 return CompletableFuture.completedFuture(list1);
	};
	@Async("asyncExecutor")
	public CompletableFuture<List<String>> call3(){
		System.out.println("Execute method asynchronously 3- "
			      + Thread.currentThread().getName());
		System.out.println("AsynchServiceImpl :call3");
		String[] strar3={"Baba","Maa"};
		List<String> list1=Arrays.asList(strar3);
		 return CompletableFuture.completedFuture(list1);
	};
	@Async("asyncExecutor")
	public CompletableFuture<SmartOutputVariableResponseWrapper> loadOutput(){
		SmartRestTemplate restTemplate= new SmartRestTemplate(authenticationUser, authenticationPassword);
		String loadSmartOutputSheetServiceUrl = smartServiceUrl + "/getSmartOutputVariables";
		SmartOutputVariableResponseWrapper wrapper=restTemplate.getForObject(loadSmartOutputSheetServiceUrl,SmartOutputVariableResponseWrapper.class);
		return CompletableFuture.completedFuture(wrapper);
	};
	@Async("asyncExecutor")
	public CompletableFuture<SmartInputVariableResponseWrapper> loadInput(){
		SmartRestTemplate restTemplate= new SmartRestTemplate(authenticationUser, authenticationPassword);
		String loadSmartInputSheetServiceUrl = smartServiceUrl + "/loadInputSheetDetails";
		SmartInputVariableResponseWrapper wrapper=restTemplate.getForObject(loadSmartInputSheetServiceUrl,SmartInputVariableResponseWrapper.class);
		return CompletableFuture.completedFuture(wrapper);
	};
	
	public SmartOutputVariableResponseWrapper loadOutput2(){
		SmartRestTemplate restTemplate= new SmartRestTemplate(authenticationUser, authenticationPassword);
		String loadSmartOutputSheetServiceUrl = smartServiceUrl + "/getSmartOutputVariables";
		SmartOutputVariableResponseWrapper wrapper=restTemplate.getForObject(loadSmartOutputSheetServiceUrl,SmartOutputVariableResponseWrapper.class);
		return wrapper;
	};
	
	public SmartInputVariableResponseWrapper loadInput2(){
		SmartRestTemplate restTemplate= new SmartRestTemplate(authenticationUser, authenticationPassword);
		String loadSmartInputSheetServiceUrl = smartServiceUrl + "/loadInputSheetDetails";
		SmartInputVariableResponseWrapper wrapper=restTemplate.getForObject(loadSmartInputSheetServiceUrl,SmartInputVariableResponseWrapper.class);
		return wrapper;
	};

}
