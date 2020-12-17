package Listener_Demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


//Below line is important. package name.classname.class
//https://www.guru99.com/listeners-selenium-webdriver.html
@Listeners(Listener_Demo.ListenerTestTestNGExample.class)

public class ListenerTestDriverTestNG {
    WebDriver driver;

    public ListenerTestDriverTestNG(){
        System.setProperty("webdriver.gecko.driver", "/home/ashwin/Desktop/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void Login(){
        driver.get("http://demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("mngr34926");
        driver.findElement(By.name("password")).sendKeys("amUpenu");
        driver.findElement(By.name("btnLogin")).click();
    }

    @Test
    public void TestToFail(){
        System.out.println("This method to test fail");
        Assert.assertTrue(false);
    }
}
