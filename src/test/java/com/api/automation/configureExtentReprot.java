package com.api.automation;

import java.util.List;
import java.util.stream.Stream;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.intuit.karate.Results;
import com.intuit.karate.core.Result;
import com.intuit.karate.core.ScenarioResult;
import com.intuit.karate.core.Step;

public class configureExtentReprot {

	private ExtentReports extentReports;
	private ExtentSparkReporter extentSparkReporter;
	private String reportDir;
	private String reportTitle = "Karate Test Excution Extent Report";
	private Results testresults;
	private ExtentTest featureNode;
	private String featureTitle = "";
	private ExtentTest scenarioNode;
	private String scenarioTitle = "";

	public configureExtentReprot() {
		extentReports = new ExtentReports();
	}

	public configureExtentReprot withReportDir(String reportDir) {
		this.reportDir = reportDir;
		return this;
	}

	public configureExtentReprot withExecutionResult(Results testresults) {
		this.testresults = testresults;
		return this;
	}

	public configureExtentReprot withReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
		return this;
	}
	public void generateExtentReport() {
		// 1. Check for ReportDir and TestResults, if not present then throw Exception

		if (this.reportDir != null && !this.reportDir.isEmpty() && this.testresults != null) {
			extentSparkReporter = new ExtentSparkReporter(reportDir);
			extentReports.attachReporter(extentSparkReporter);
			setConfig();
			
			// 2. Using the testReults, Get the list of scenario results
			Stream<ScenarioResult> scenarioResults = getScenarioResults();
			// 3. loop over list of scenario results
			scenarioResults.forEach((scenarioResult) -> {
				// 4. Using Scenario result, get the scenario object
				// 5. Using the Scenario object, get the info about feature file
				String featureName = getFeatureName(scenarioResult);
				String featureDesc = getFeatureDesc(scenarioResult);
				ExtentTest featureNode = createFeatureNode(featureName, featureDesc);
				// 6. Using the same scenario object, we will get the info about the scenario
				String scenarioTitle = getSecnarioTitle(scenarioResult);
				ExtentTest scenarioNode = createScenarioNode(featureNode,"<b>SCENARIO TITLE :   </b>"+ scenarioTitle);
				// 7. Using the Scenario Result get the list of step result
				// 8. loop over the step result list, get the info about scenario step and its
				// execution status
				scenarioResult.getStepResults().forEach((step) -> {
					// Adding the Scenario Step with Scenario node
					addScenarioStep(scenarioNode, step.getStep(), step.getResult());
				});
			});
			// 9. Use all the info to generate the extent report.
			extentReports.flush();
			return;
		}
		throw new RuntimeException("Missing the Karate Test Result / Report Dir location");
	}

	private Stream<ScenarioResult> getScenarioResults() {
		return this.testresults.getScenarioResults();
	}

	private String getFeatureName(ScenarioResult scenarioResult) {
		return scenarioResult.getScenario().getFeature().getName();
	}

	private String getFeatureDesc(ScenarioResult scenarioResult) {
		return scenarioResult.getScenario().getFeature().getDescription();
	}

	private ExtentTest createFeatureNode(String featureName, String featureDesc) {
		// if the title of feature is same, I will return same instance of extent test
		// else I will create a new instance and then return it

		if (this.featureTitle.equalsIgnoreCase(featureName)) {
			return featureNode;
		}
		this.featureTitle = featureName;
		featureNode = extentReports.createTest(Feature.class,"<b>Feature: </b>" +featureName, featureDesc);
		return featureNode;
	}

	private ExtentTest createScenarioNode(ExtentTest featureNode, String scenarioTitle) {
		// if the title of scenario is same, I will return same instance of extent test
		// else I will create a new instance and then return it

		if (this.scenarioTitle.equalsIgnoreCase(scenarioTitle)) {
			return scenarioNode;
		}
		this.scenarioTitle = scenarioTitle;
		scenarioNode = featureNode.createNode(Scenario.class,scenarioTitle);
		return scenarioNode;
	}

	private String getSecnarioTitle(ScenarioResult scenarioResult) {
		return scenarioResult.getScenario().getName();

	}

	private void addScenarioStep(ExtentTest scenarioNode, Step step, Result stepResult) {
		String type = step.getPrefix(); // Given, When or Then
		String stepTitle = step.getText();
		String status = stepResult.getStatus();
		Throwable error = stepResult.getError();
		ExtentTest stepNode;

		switch (type) {
		case "Given":
			//stepNode = scenarioNode.info(MarkupHelper.createLabel("<b>stepTitle :   </b>" + stepTitle,  ExtentColor.BLUE));//createNode(Given.class, stepTitle);
			stepNode = scenarioNode.createNode(When.class, stepTitle);
			scenarioNode.log(Status.INFO, stepTitle);
			addStatus(stepNode, status, error);
			
			break;
		case "When":
			stepNode = scenarioNode.createNode(When.class, stepTitle);
			addStatus(stepNode, status, error);
			break;
		case "Then":
			stepNode = scenarioNode.createNode(Then.class, stepTitle);
			addStatus(stepNode, status, error);
			break;
		case "And":
			stepNode = scenarioNode.createNode(And.class, stepTitle);
			addStatus(stepNode, status, error);
			break;

		default:
			stepNode = scenarioNode.createNode(type + " " + stepTitle);
			addStatus(stepNode, status, error);
			break;
		}
	}

	private void addStatus(ExtentTest stepNode, String status, Throwable error) {
		switch (status) {
		case "passed":
			stepNode.pass("");
			break;
		case "failed":
			stepNode.fail(error);
			break;
		default:
			stepNode.skip("");
			break;
		}
	}

	private void setConfig() {
		extentSparkReporter.config().enableOfflineMode(true);
		extentSparkReporter.config().setDocumentTitle("Naveen Test Report");
		extentSparkReporter.config().setTimelineEnabled(true);
		//extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		extentSparkReporter.config().setEncoding("utf-8");
		extentSparkReporter.viewConfigurer().viewOrder().as(
	                new ViewName[] {
	                        ViewName.DASHBOARD,
	                        ViewName.TEST,
	                        ViewName.AUTHOR,
	                        ViewName.DEVICE,
	                        ViewName.EXCEPTION,
	                        ViewName.LOG
	                }).apply();
	}

}