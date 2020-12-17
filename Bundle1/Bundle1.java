import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;


public class Bundle1 {

    WebDriver driver;
    String url = "http://demo.guru99.com/test/radio.html";
    String url2 = "http://demo.guru99.com/test/newtours/register.php";
    String url3 = "http://demo.guru99.com/test/newtours/";
    String url4 = "http://demo.guru99.com/test/upload/";
    String url5 = "http://demo.guru99.com/test/delete_customer.php";
    String url6 = "https://www.zlti.com/";
    String url7 = "https://demoqa.com/text-box";

    public Bundle1(){

        System.setProperty("webdriver.gecko.driver", "/home/ashwin/Desktop/geckodriver");
        driver = new FirefoxDriver();
    }

    public void Wait(int millisecond){
        try{
            Thread.sleep(millisecond);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void VerifyCheckBoxRadioButton(){

        driver.get(url);

        WebElement radioButton2 = driver.findElement(By.id("vfb-7-2"));
        WebElement radioButton3 = driver.findElement(By.id("vfb-7-3"));

        radioButton2.click();
        Wait(2000);
        System.out.println("RadioButton2 is selected");
        radioButton3.click();
        Wait(2000);
        System.out.println("RadioButton3 is selected");


        WebElement optionBox1 = driver.findElement(By.id("vfb-6-0"));
        WebElement optionBox2 = driver.findElement(By.id("vfb-6-1"));

        for(int i=0; i<2; i++){
            Wait(2000);
            optionBox1.click();
            System.out.println("Is optionBox1 selected: "+optionBox1.isSelected());
        }

    }

    @Test(priority = 2)
    public void DropDownList(){

        //We use a class org.openqa.selenium.support.ui.Select

        driver.get(url2);
        WebElement dropDownName = driver.findElement(By.name("country"));
        Select selectObject = new Select(dropDownName);
        //selectObject.deselectAll(); //will throw an exception as you cannot deselet all

        System.out.println("Is the drop down list allow multiple selection?");
        if(selectObject.isMultiple()){
            System.out.println("Allows multiple selection");
        }
        else if(!selectObject.isMultiple()){
            System.out.println("Does not allow multiple select");
        }

        //Now I have to select a value. There are methods like
        //selectByIndex(int index)
        //selectByValue(String value)
        //selectByVisibleText(String text)

        selectObject.selectByVisibleText("AUSTRALIA");
        //Now lets select by value
        Wait(2000);
        selectObject.selectByValue("ARMENIA");
        Wait(2000);
        selectObject.selectByIndex(1);

        //Lets get a list of options
        List<WebElement> list = selectObject.getOptions();
        for(WebElement e : list){
            System.out.println(e.getText()); //Here we have the WebElement and now we need to use WebElement methods to perform actions
        }

    }

    public void MouseClickKeyboardEvent(){

        //For interactions we need Action and Actions class.
        //Here inspect element was not found in Firefox. So switched to chrome and chropath
        driver.get(url3);
        Actions actionObject = new Actions(driver);
        WebElement link_Home = driver.findElement(By.linkText("Home"));
        WebElement tdOfHome = driver.findElement(By.xpath("//html/body/div"
                        + "/table/tbody/tr/td"
                        + "/table/tbody/tr/td"
                        + "/table/tbody/tr/td"
                        + "/table/tbody/tr"));

        //This above value is straight from tutorial . But Below value is practically obtained through inspect

        //"/html/body/div[2]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[1]"

        //Action is a class and there is only one method perform. But Actions methods throw Action
        Action mouseOver = actionObject.moveToElement(link_Home).build();

        String bgColour = tdOfHome.getCssValue("background-color");
        System.out.println("Background colour of tdOfHome is: "+bgColour);

        mouseOver.perform();

        String colorNow = tdOfHome.getCssValue("background-color");
        System.out.println("Background colour of tdOfHome when hovered because perform has been executed"+colorNow);

        //its not showing the color before hover correctly. its a guru99 selenium tutorial
    }


    public void Upload(){

        driver.get(url4);
        WebElement browseButton = driver.findElement(By.name("uploadfile_0"));
        browseButton.sendKeys("/home/ashwin/IdeaProjects/Selenium Concepts/Bundle1/ToUploadFile");
        Wait(2000);
        WebElement terms = driver.findElement(By.id("terms"));
        terms.click();
        Wait(2000);
        WebElement submit = driver.findElement(By.id("submitbutton"));
        submit.click();
    }


    public void AlertAndPopUp(){

        //Here we use import org.openqa.selenium.Alert
        driver.get(url5);
        WebElement inputBox = driver.findElement(By.name("cusid"));
        inputBox.sendKeys("452");
        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();

        //Now open Alert class api and read

        //We have to switch to alert

        Alert alert = driver.switchTo().alert();

        String getText = driver.switchTo().alert().getText();
        System.out.println("Text of alert is: "+getText);

        //Now accept

        driver.switchTo().alert().accept();
        Wait(2000);
        driver.switchTo().alert().accept();
    }

    public void BrokenLinks(){

        int responseCode;
        HttpURLConnection huc = null;
        driver.get(url6);
        String url = "";
        List<WebElement> list = driver.findElements(By.tagName("a"));
        Iterator<WebElement> it = list.iterator();

        while(it.hasNext()){
            url = it.next().getAttribute("href");
            System.out.println(url);

            if(url.isEmpty() || url == null){
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if(!url.startsWith(url6)){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }

            try{
               huc = (HttpURLConnection)new URL(url).openConnection();
               huc.setRequestMethod("HEAD");
               huc.connect();

               responseCode = huc.getResponseCode();

               if(responseCode >=400) System.out.println("Link is broken");
               else if(responseCode == 200) System.out.println("Valid link");


            }catch(MalformedURLException ex){
                ex.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
            }

        }

    }

    public void KeyboardActions(){

        driver.get(url7);
        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys("Mr.Peter Haynes.");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("PeterHaynes@toolsqa.com");

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("43 School Lane London EC71 9GO.");

        currentAddress.sendKeys(Keys.CONTROL);
        currentAddress.sendKeys("A");
        currentAddress.sendKeys(Keys.CONTROL);
        currentAddress.sendKeys("C");


        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys(Keys.CONTROL);
        permanentAddress.sendKeys("V");

        //The above steps will not work so we need Actions class

        Actions builder = new Actions(driver);
        currentAddress.click();
        builder.keyDown(Keys.CONTROL);
        builder.sendKeys("a");
        builder.keyUp(Keys.CONTROL);

        builder.keyDown(Keys.CONTROL);
        builder.sendKeys("c");
        builder.keyUp(Keys.CONTROL);
        builder.build().perform();

        permanentAddress.click();
        permanentAddress.clear();
        permanentAddress.click();

        builder.keyDown(Keys.CONTROL);
        builder.sendKeys("v");
        builder.keyUp(Keys.CONTROL);
        builder.build().perform();

        assertEquals(currentAddress.getAttribute("value"),permanentAddress.getAttribute("value"));

    }


    public static void main(String[] arg){

        Bundle1 object = new Bundle1();
        //object.VerifyCheckBoxRadioButton();
        //object.DropDownList();
        //object.MouseClickKeyboardEvent();
        //object.Upload();
        //object.AlertAndPopUp();
        //object.BrokenLinks();
        object.KeyboardActions();

     }
}
