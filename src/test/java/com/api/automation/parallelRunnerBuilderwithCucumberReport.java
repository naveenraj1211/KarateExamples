package com.api.automation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.junit.Assert;
import org.junit.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner.Builder;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class parallelRunnerBuilderwithCucumberReport {

	@Test
	public void testAll() {

		Builder runner = new Builder();
		Results results = runner.path("classpath:com/api/automation").outputCucumberJson(true).parallel(5);

		System.out.println("Total Features: " + results.getFeaturesTotal());
		System.out.println("Total Features Passed: " + results.getFeaturesPassed());
		System.out.println("Total Features Failed: " + results.getFeaturesFailed());

		System.out.println("Total Scenarios: " + results.getScenariosTotal());
		System.out.println("Total Scenarios Passed: " + results.getScenariosPassed());
		System.out.println("Total Scenarios Failed: " + results.getScenariosFailed());

		System.out.println("Total Failed: " + results.getFailCount());
		generateReport(results.getReportDir());
		System.out.println("Report Location: " + results.getReportDir());
		Assert.assertTrue( "There are some failed steps",results.getFailCount() == 0);

	}

	public void generateReport(String karateReportPath) {

		// First we need to filter out all the json file path in report directory
		File reportDir = new File(karateReportPath);
		Collection<File> jsonFiles = FileUtils.listFiles(reportDir, new String[] { "json" }, true);
		List<String> jsonPath = new ArrayList<String>();
		jsonFiles.forEach(file -> jsonPath.add(file.getAbsolutePath()));

		// second step configuration class
		Configuration configuration = new Configuration(reportDir, "Karate Demo Project");

		// third step report builder
		ReportBuilder reportBuilder = new ReportBuilder(jsonPath, configuration);
		reportBuilder.generateReports();

	}

}