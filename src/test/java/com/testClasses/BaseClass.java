package com.testClasses;

import com.utilities.ReadConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
    public static WebDriver driver;
    public static Logger logger;

    ReadConfig readconfig=new ReadConfig();

    public String baseURL = readconfig.gerConfig("baseURL");
    public String username = readconfig.gerConfig("username");
    public String password = readconfig.gerConfig("password");

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {

        if(browser.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",readconfig.gerConfig("chromepath"));
            driver=new ChromeDriver();
        }
        else if(browser.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver",readconfig.gerConfig("iepath"));
            driver = new FirefoxDriver();
        }
        else if(browser.equals("ie"))
        {
            System.setProperty("webdriver.ie.driver",readconfig.gerConfig("firefoxpath"));
            driver = new InternetExplorerDriver();
        }

        logger = Logger.getLogger("LoggerName");
        PropertyConfigurator.configure("log4j.properties");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
