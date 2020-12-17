
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.Assert;

public class VerifyTitleTestNG {

    WebDriver driver;
    String baseUrl = "http://demo.guru99.com/test/newtours/";
    String driverPath = "/home/ashwin/Desktop/geckodriver";

    public VerifyTitleTestNG(){
        System.setProperty("webdriver.gecko.driver",driverPath);
        driver = new FirefoxDriver();
    }

    @Test(priority = 1)
    public void VerifyTitle(){

        driver.get(baseUrl);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Welcome: Mercury Tours";
        Assert.assertEquals(actualTitle,expectedTitle,"Titles do not match");



    }

    /*
    We can use multiple @Test annotations in a single TestNG file. By default, methods annotated by @Test are executed
    alphabetically. See the code below. Though the methods c_test, a_test, and b_test are not arranged alphabetically
    in the code, they will be executed as such.

    Or write the methods and put priority
     */
}
