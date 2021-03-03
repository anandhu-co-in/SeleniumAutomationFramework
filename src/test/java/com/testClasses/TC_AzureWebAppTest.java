package com.testClasses;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TC_AzureWebAppTest extends BaseClass {


    @Test
    public void counterTest() throws InterruptedException {
        System.out.println("Executed test");

        driver.get("https://reactappfordemo.azurewebsites.net/");
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button")).click();
        Thread.sleep(1000);
    }


}
