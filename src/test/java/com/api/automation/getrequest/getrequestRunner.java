package com.api.automation.getrequest;

import org.junit.Test;

import com.intuit.karate.Runner;

public class getrequestRunner
{
	//schemaValidation , queryParameter , localvariables
	
	
	@Test
	public void runTestUsingRelativeTo() 
	{
		Runner.path("queryParameter").relativeTo(getClass());
	}
	
	//@Test
	public void runTestUsingClasspath() 
	{
		Runner.path("classpath:com/api/automation/getrequest/localvariables.feature");
	}
	
}