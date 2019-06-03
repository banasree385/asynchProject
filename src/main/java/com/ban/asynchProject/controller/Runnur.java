package com.ban.asynchProject.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ban.asynchProject.service.TestService;

@Component
public class Runnur implements CommandLineRunner{

	@Autowired
	@Qualifier("testService")
	private TestService testService;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
        long start = System.currentTimeMillis();
        CompletableFuture<List<String>>list1= testService.call1();
        
        CompletableFuture<List<String>>list2= testService.call2();
        CompletableFuture<List<String>>list3= testService.call3();
        CompletableFuture.allOf(list1, list2, list3).join();
		
	}

}
