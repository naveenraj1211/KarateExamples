package com.api.automation.callfeature;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

public class callFeatureRunner {
    
    @Test
    public void testParallel() 
    {
        Results results = Runner.path("classpath:com/api/automation/callfeature/callingSceanrioInanotherFeature.feature")
        		.parallel(1);
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    }
    
}