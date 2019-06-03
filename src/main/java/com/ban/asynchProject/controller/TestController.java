package com.ban.asynchProject.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ban.asynchProject.service.TestService;
import com.ge.capital.efs.smart_msexcel_beans.response.SmartInputVariableResponseWrapper;
import com.ge.capital.efs.smart_msexcel_beans.response.SmartOutputVariableResponseWrapper;

@RestController
public class TestController {

	
	@Autowired
	@Qualifier("testService")
	private TestService testService;
	
	@RequestMapping(value = "/call1", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
	public @ResponseBody List<String> call1()throws Exception
	{
		 CompletableFuture<List<String>> future1=testService.call1();		
		 CompletableFuture<List<String>>future2=testService.call2();
		 CompletableFuture<List<String>> future3=testService.call3();
        CompletableFuture.allOf(future1, future2, future3).join();

         List<String> list1=future1.get();
         List<String> list2=future2.get();
        // list1.addAll(list2);
		// Wait until they are all done
		return list1;
	}
	@RequestMapping(value = "/loadVars", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
	public @ResponseBody HashMap<String, Object> loadVars()throws Exception
	{
		long start = System.currentTimeMillis();
		HashMap<String, Object> variableDetails = new HashMap<String, Object>();
		CompletableFuture<SmartInputVariableResponseWrapper> inputFuture=testService.loadInput();
		CompletableFuture<SmartOutputVariableResponseWrapper> outputFuture=testService.loadOutput();
        CompletableFuture.allOf(inputFuture, inputFuture).join();
        variableDetails.put("smartInputSheetFirstGrid", inputFuture.get().getSmartInputVariableFirstGrids());
        variableDetails.put("smartInputSheetSecondGrid", inputFuture.get().getSmartInputVariableSecondGrids());
        variableDetails.put("smartInputSheetThirdGrid", inputFuture.get().getSmartInputVariableThirdGrids());
        variableDetails.put("smartOutputSheetThirdGrid", outputFuture.get().getSmartOutputVariable());
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        return variableDetails;
	}
	@RequestMapping(value = "/loadVars2", method = RequestMethod.GET,produces = { "application/json;charset=utf-8" })
	public @ResponseBody HashMap<String, Object> loadVars2()throws Exception
	{
		long start = System.currentTimeMillis();

		HashMap<String, Object> variableDetails = new HashMap<String, Object>();
		SmartInputVariableResponseWrapper input=testService.loadInput2();
		SmartOutputVariableResponseWrapper output=testService.loadOutput2();
		 variableDetails.put("smartInputSheetFirstGrid", input.getSmartInputVariableFirstGrids());
	     variableDetails.put("smartInputSheetSecondGrid", input.getSmartInputVariableSecondGrids());
	     variableDetails.put("smartInputSheetThirdGrid", input.getSmartInputVariableThirdGrids());
	     variableDetails.put("smartOutputSheetThirdGrid", output.getSmartOutputVariable());
	      System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        return variableDetails;
	}
	

}
