package com.api.automation.fileupload;

import org.junit.Test;
import com.intuit.karate.Runner;

public class fileuploadRunner
{
	@Test
	public void testAll()
	{
		Runner.path("fileUploadRequest").relativeTo(getClass());
		
	}
}