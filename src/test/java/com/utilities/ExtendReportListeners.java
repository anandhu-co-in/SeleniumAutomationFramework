package com.utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testClasses.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtendReportListeners extends BaseClass implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        extendReportLogger = extent.createTest(result.getName());
        extendReportLogger.log(Status.INFO, "Test Started");
        extent.flush();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extendReportLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extendReportLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "\\TestResults\\Screenshots\\" + result.getName()+"_"+timeStamp + ".png";

        try {
            captureScreen(driver,screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(screenshotPath);

        if (f.exists()) {
            try {
                //The below one line, I added it to make the screenshot path in the html file relative. So i can move the folder
                screenshotPath=screenshotPath.replace(System.getProperty("user.dir") + "\\TestResults\\","");
                extendReportLogger.fail("Failure Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } catch (Exception e) {
                logger.info("Failed to attach screenshot to extend report" + e.getLocalizedMessage());
            }
        }

        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extendReportLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
        extent.flush();
    }


}
