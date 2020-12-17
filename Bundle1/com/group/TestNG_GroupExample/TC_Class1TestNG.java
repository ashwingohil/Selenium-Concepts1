package com.group.TestNG_GroupExample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

//https://www.guru99.com/introduction-testng-groups.html

//User credentials for the website works only for 20 days. you need to create new credentials
//by going at http://www.demo.guru99.com/ and entering email id you will get the credentials. Update that here

//As always first class is to be mentioned in xml. and Run as normal
//Then next scenario is . In first scenario(basic - running all methods) comment the groups tag . In next scenario
//uncomment groups and run. It still run all the tests when include so basically this is the concept

public class TC_Class1TestNG {

    WebDriver driver;
    String driverPath = "/home/ashwin/Desktop/geckodriver";
    String baseUrl = "http://www.demo.guru99.com/V4/";
    String homepageHeading = "//h2[@class='barone']";
    String managerID = "//td[contains(text(),'Manger Id')]"; //how to know this is to be learnt. not just inspect but to form it
    String usernameTextbox = "//input[@type='text']";
    String passwordTextbox = "//input[@type='password']";
    String signInButton = "//input[@type='submit']";
    String usernameValue = "mngr298340";
    String passwordValue = "tequjaj";
    String newCustomerLink = "//a[contains(text(),'New Customer')]";
    String fundTransferLink = "//a[contains(text(),'Fund Transfer')]";

    public TC_Class1TestNG(){
        System.setProperty("webdriver.gecko.driver",driverPath);
        driver = new FirefoxDriver();
    }

    @Test(groups = {"bonding", "strong_ties"})
    public void tc01_toLaunchURL(){
        driver.get(baseUrl);
    }

    @Test(groups = {"bonding"})
    public void tc02_toVerifyLaunchPage(){
        Assert.assertTrue(driver.findElement(By.xpath(homepageHeading)).isDisplayed(), "Homepage heading not displayed");
        //Here we use Assert.assertTrue. Here second parameter is string to display if assert fails else nothing shows on console
        System.out.println("Page heading is displayed");
    }

    @Test(groups = {"bonding","strong_ties"})
    public void tc03_toEnterCredentials(){

        WebElement username = driver.findElement(By.xpath(usernameTextbox));
        username.sendKeys(usernameValue);
        WebElement password = driver.findElement(By.xpath(passwordTextbox));
        password.sendKeys(passwordValue);
        WebElement signin = driver.findElement(By.xpath(signInButton));
        signin.click();

    }

    @Test(groups = {"strong_ties"})
    public void tc04_toVerifyLoggedInPage(){
        Assert.assertTrue(driver.findElement(By.xpath(managerID)).isDisplayed(), "Manager ID label is not displayed");
        System.out.println("Manager ID label is displayed");
    }


    @Test(groups = {"bonding"})
    public void tc05_VerifyHyperLinks(){

        Assert.assertTrue(driver.findElement(By.xpath(newCustomerLink)).isEnabled(), "Customer link is not displaye");
        System.out.println("New customer link is displayed");
        Assert.assertTrue(driver.findElement(By.xpath(fundTransferLink)).isEnabled(),"Fund Transfer link is not displayed");
        System.out.println("Fund Transfer link is displayed");
    }
}
