package com.testClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {
    public static WebDriver driver;
    public static Logger logger;
    public static ExtentTest extendReportLogger;

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;

    ReadConfig readconfig = new ReadConfig();
    public String baseURL = readconfig.gerConfig("baseURL");
    public String username = readconfig.gerConfig("username");
    public String password = readconfig.gerConfig("password");

    @BeforeSuite
    public void suitStartSetup() {
        System.out.println("Executing beforesuit");
        logger = Logger.getLogger("LoggerName");
        PropertyConfigurator.configure("log4j.properties");
        createExtendReport();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) {

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", readconfig.gerConfig("chromepath"));
            driver = new ChromeDriver();
        } else {
            throw new RuntimeException("The specified browser is not supported by the framework : " + browser);
        }

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    //Create Report
    public void createExtendReport() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        String repName = "Test-Report-" + timeStamp + ".html";

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/TestResults/" + repName);//specify location of the report
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environemnt", "QA");
        extent.setSystemInfo("user", "Anandhu");

        htmlReporter.config().setDocumentTitle("Test Execution Results"); // Tile of report
        htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
        htmlReporter.config().setTheme(Theme.STANDARD);
    }


    //Utility Methods
    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/TestResults/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }


}
