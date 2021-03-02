package com.utilities;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.testClasses.BaseClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtendReportListeners extends BaseClass implements ITestListener  {

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
        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extendReportLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
        extent.flush();
    }


}
