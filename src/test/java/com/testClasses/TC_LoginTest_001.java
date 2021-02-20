package com.testClasses;

import com.pageObjects.LoginPage;
import com.testClasses.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_LoginTest_001 extends BaseClass
{

    @Test
    public void loginTest()
    {

        driver.get(baseURL);

        logger.info("URL is opened");

        LoginPage lp=new LoginPage(driver);
        lp.setUserName(username);
        lp.setPassword(password);
        lp.clickSubmit();


        if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
        {
            Assert.assertTrue(true);
            logger.info("Test Passed");
        }
        else
        {
            Assert.assertTrue(false);
            logger.info("Test Faile");
        }

    }
}