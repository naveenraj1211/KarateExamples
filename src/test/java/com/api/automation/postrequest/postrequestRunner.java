package com.api.automation.postrequest;

import org.junit.Test;

import com.intuit.karate.Runner;

public class postrequestRunner {

	// @Test
	public void test() {
		Runner.path("demoPOSTrequest").relativeTo(getClass());

	}

	// excuteJSfunction //embeddedExpressions //jsonPathExpression
	@Test
	public void runTestUsingClasspath() {
		Runner.path("classpath:com/api/automation/postrequest/jsonPathExpression.feature");
	}

}