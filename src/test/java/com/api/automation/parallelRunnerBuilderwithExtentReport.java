package com.api.automation;


import com.api.automation.configureExtentReprot;
import org.junit.Assert;
import org.junit.Test;
import com.intuit.karate.Results;
import com.intuit.karate.Runner.Builder;


public class parallelRunnerBuilderwithExtentReport {

	@Test
	public void testAll() {

		Builder runner = new Builder();
		Results results = runner.path("classpath:com/api/automation/getrequest").outputCucumberJson(true).parallel(5);

		System.out.println("Total Features: " + results.getFeaturesTotal());
		System.out.println("Total Features Passed: " + results.getFeaturesPassed());
		System.out.println("Total Features Failed: " + results.getFeaturesFailed());

		System.out.println("Total Scenarios: " + results.getScenariosTotal());
		System.out.println("Total Scenarios Passed: " + results.getScenariosPassed());
		System.out.println("Total Scenarios Failed: " + results.getScenariosFailed());

		System.out.println("Total Failed: " + results.getFailCount());
		
		System.out.println("Report Location: " + results.getReportDir());
		
		
		configureExtentReprot extentReprot  = new configureExtentReprot()
				.withExecutionResult(results)
				.withReportDir(results.getReportDir())
				.withReportTitle("Karate Test Execution Extent Report");
		extentReprot.generateExtentReport();
				
		Assert.assertTrue( "There are some failed steps",results.getFailCount() == 0);		

	}


}