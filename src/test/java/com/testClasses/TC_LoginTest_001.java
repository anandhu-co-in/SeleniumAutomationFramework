package com.testClasses;

import com.pageObjects.LoginPage;
import com.testClasses.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass
{

    @Test
    public void loginTest() throws IOException {
        driver.get(baseURL);
        logger.info("URL is opened");
        LoginPage lp=new LoginPage(driver);
        lp.setUserName(username);
        lp.setPassword(password);
        logger.info("Entered username and password");

        if(driver.getTitle().equals("Guru99 Bank Home Page"))
        {
            Assert.assertTrue(true);
            logger.info("Test Passed");
        }

        else
        {
            logger.info("Test Failed");
            captureScreen(driver,"loginTest");
            Assert.assertTrue(false);
        }

    }
}