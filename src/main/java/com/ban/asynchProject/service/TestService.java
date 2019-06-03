package com.ban.asynchProject.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.ge.capital.efs.smart_msexcel_beans.response.SmartInputVariableResponseWrapper;
import com.ge.capital.efs.smart_msexcel_beans.response.SmartOutputVariableResponseWrapper;


public interface TestService  {
	public CompletableFuture<List<String>> call1()throws InterruptedException;
	public CompletableFuture<List<String>> call2();
	public CompletableFuture<List<String>> call3();
	CompletableFuture<SmartOutputVariableResponseWrapper> loadOutput();
	CompletableFuture<SmartInputVariableResponseWrapper> loadInput();
	SmartOutputVariableResponseWrapper loadOutput2();
	SmartInputVariableResponseWrapper  loadInput2();

}
