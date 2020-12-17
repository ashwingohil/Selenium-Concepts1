import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

//If you only write annotation @Test above each test method then it will execute them in alphabetical order
//of method names which will not be in order of the flow of methods as expected. Few tests will fail
//So we need to put priority
public class PriorityTestNGExample {

    WebDriver driver;
    String baseUrl = "https://www.google.com/";
    String searchBox = "//input[@type='text']";

    public PriorityTestNGExample(){
        System.setProperty("webdriver.gecko.driver", "/home/ashwin/Desktop/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test(priority = 1)
    public void LaunchGooglePage(){
        driver.get(baseUrl);
    }

    @Test(priority = 2)
    public void PerformSearchAndClick(){

        WebElement Search = driver.findElement(By.xpath(searchBox));
        Search.sendKeys("Facebook");
    }

    @Test(priority = 3)
    public void VerifyGoogleSearchPage(){
        WebElement SearchButton = driver.findElement(By.xpath("//body[@id='gsr']/div[@id='viewport']/div[@id='searchform']/form[@id='tsf']/div[@jsmodel='vWNDde']/div[@class='A8SBwf']/div[@class='FPdoLc tfB0Bf']/center/input[1]"));

        SearchButton.click();

        try{
            Thread.sleep(2000);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
        Assert.assertEquals(driver.getTitle(), "Facebook - Google Search", "Title does not match");
    }

}
