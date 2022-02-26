package com.api.automation;


import org.junit.Test;
import org.junit.Assert;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

//import com.intuit.karate.Runner.Builder;

public class parallelRunner {

	@Test
	public void testAll() {

		// classpath:com/api/automation

		// Runner.parallel(getClass(), 5);
		Results results = Runner.path("classpath:com/api/automation").parallel(5);

		System.out.println("Total Features: " + results.getFeaturesTotal());
		System.out.println("Total Features Passed: " + results.getFeaturesPassed());
		System.out.println("Total Features Failed: " + results.getFeaturesFailed());

		System.out.println("Total Scenarios: " + results.getScenariosTotal());
		System.out.println("Total Scenarios Passed: " + results.getScenariosPassed());
		System.out.println("Total Scenarios Failed: " + results.getScenariosFailed());

		System.out.println("Total Failed: " + results.getFailCount());
		//Assert.assertTrue(results.getFailCount()== 0, "There are some failed steps");
		Assert.assertTrue("There are some failed steps", results.getFailCount()== 0);
	

	}
}
