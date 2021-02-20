package com.testClasses;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    public static WebDriver driver;

    public static Logger logger;

    public String baseURL = "http://demo.guru99.com/v4/";
    public String username = "abcd";
    public String password = "password";

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/BrowserDrivers/chromedriver.exe");
        driver = new ChromeDriver();

        logger = Logger.getLogger("LoggerName");
        PropertyConfigurator.configure("log4j.properties");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
