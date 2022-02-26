package com.api.automation;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.Assert;

import com.intuit.karate.Results;
import com.intuit.karate.Runner.Builder;

import cucumber.api.CucumberOptions;

public class parallelRunnerBuilder {

	//@CucumberOptions(plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
	@Test
	public void testAll() {

		Builder runner = new Builder();
		Results results = runner.path("classpath:com/api/automation").parallel(5);

		System.out.println("Total Features: " + results.getFeaturesTotal());
		System.out.println("Total Features Passed: " + results.getFeaturesPassed());
		System.out.println("Total Features Failed: " + results.getFeaturesFailed());

		System.out.println("Total Scenarios: " + results.getScenariosTotal());
		System.out.println("Total Scenarios Passed: " + results.getScenariosPassed());
		System.out.println("Total Scenarios Failed: " + results.getScenariosFailed());

		System.out.println("Total Failed: " + results.getFailCount());
		//Assertions.assertEquals(0, results.getFailCount(), "There are some failed steps");
		Assert.assertTrue("There are some failed steps", results.getFailCount()== 0);

	}

}