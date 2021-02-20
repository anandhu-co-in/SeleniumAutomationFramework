package com.testClasses;

import com.utilities.ReadConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    public static WebDriver driver;
    public static Logger logger;

    ReadConfig readconfig=new ReadConfig();

    public String baseURL = readconfig.gerConfig("baseURL");
    public String username = readconfig.gerConfig("username");
    public String password = readconfig.gerConfig("password");

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", readconfig.gerConfig("chromepath"));
        driver = new ChromeDriver();

        logger = Logger.getLogger("LoggerName");
        PropertyConfigurator.configure("log4j.properties");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
