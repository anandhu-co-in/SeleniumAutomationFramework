package com.testClasses;
import com.aventstack.extentreports.Status;
import com.pageObjects.HomePage;
import com.utilities.ExcelUtilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class TC_003_VerifyCountIsCorrect_DataDriven extends BaseClass {

    @Test(dataProvider = "countTestData")
    public void Test(String clicks,String counts){
//      Reporter.getCurrentTestResult().setAttribute("iterationDetail",clicks+" "+counts);
        extendReportLogger.log(Status.INFO, "Testing by clicking "+clicks+" times. Expected count is "+counts);
        driver.get(baseURL);
        HomePage home=new HomePage(driver);
        for (int i=0;i<Integer.parseInt(clicks);i++){
            home.clickPlus();
        }
        extendReportLogger.log(Status.INFO, "Actual Count is  "+home.getCount());
        Assert.assertTrue(counts.equals(home.getCount()));
        logger.info("Test Complete");
    }

    @DataProvider(name="countTestData")
    Object[][] getData() throws IOException {

        String path=System.getProperty("user.dir")+"/src/test/java/com/testData/LoginData.xlsx";
        int rownum= ExcelUtilities.getRowCount(path, "Sheet1");
        int colcount=ExcelUtilities.getCellCount(path,"Sheet1",1);
        String logindata[][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                logindata[i-1][j]=ExcelUtilities.getCellData(path,"Sheet1", i,j);//1 0
            }
        }
        return logindata;
    }

}
