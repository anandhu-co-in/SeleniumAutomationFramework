package com.testClasses;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_001_VerifyAppTitle extends BaseClass
{

    @Test
    public void loginTest() throws IOException {
        driver.get(baseURL);
        logger.info("URL is opened");

        String currentTitle=driver.getTitle();
        extendReportLogger.log(Status.INFO, "Current title is "+currentTitle);

        if(currentTitle.equals("Counter"))
        {
            Assert.assertTrue(true);
            logger.info("Test Passed, titles match");
        }
        else
        {
            logger.info("Test Failed,titles doesn't match");
            Assert.assertTrue(false);
        }
        logger.info("Test Complete");
    }
}