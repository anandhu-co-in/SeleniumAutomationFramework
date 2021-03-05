package com.testClasses;

import com.pageObjects.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_002_VerifyCounterWorks extends BaseClass {

    @Test
    public void counterTest()  {
        driver.get(baseURL);
        logger.info("URL Opened");
        HomePage home = new HomePage(driver);
        String currentCount=home.getCount();
        home.clickPlus();
        Assert.assertTrue(!currentCount.equals(home.getCount()));
        logger.info("Test Complete");
    }


}
