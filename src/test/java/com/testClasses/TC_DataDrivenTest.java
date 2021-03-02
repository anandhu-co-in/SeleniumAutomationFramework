package com.testClasses;

import com.utilities.ExcelUtilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_DataDrivenTest extends BaseClass {


    @Test(dataProvider = "LoginData")
    public void Test(String data1,String data2){
        System.out.println("Executing with "+data1+" "+data2);
    }

    @DataProvider(name="LoginData")
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
