import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyTitle2TestNG {

    WebDriver driver;
    String baseUrl = "http://demo.guru99.com/test/newtours/";
    String driverPath = "/home/ashwin/Desktop/geckodriver";
    String actual;
    String expected;

    @BeforeTest
    public void launchBrowser(){
        System.setProperty("webdriver.gecko.driver",driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
    }

    @BeforeMethod
    public void verifyHomePageTitle(){

        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle, "Expected and Actual Title does not match");
    }

    @Test(priority = 0)
    public void register(){

        WebElement registerButton = driver.findElement(By.xpath("//a[contains(text(),'REGISTER')]"));
        registerButton.click();
        actual = driver.getTitle();
        expected = "Register: Mercury Tours";
        Assert.assertEquals(actual,expected, "Register page title does not match with expected title");

    }

    @AfterMethod
    public void goBackToHomePage() {

        WebElement homeLink = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        homeLink.click();
    }

    @Test(priority = 1)
    public void support() {

        WebElement supportLink = driver.findElement(By.xpath("//a[contains(text(),'SUPPORT')]"));
        supportLink.click();
        actual = driver.getTitle();
        expected = "Under Construction: Mercury Tours";
        Assert.assertEquals(actual, expected, "Actual and expected does not match on support page");
    }

}
