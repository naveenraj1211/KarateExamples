package com.api.automation.datadriven;

import org.junit.Test;

import com.intuit.karate.Runner;

public class dataDrivenTestRunner{
	
	//dataTableinSameFeature //dataTableinExternalFile
	
	@Test
	public void testSingle()
	{
		
		Runner.path("classpath:com/api/automation/datadriven/dataTableinExternalFile.feature").parallel(1);
	
	}
}