package TestCases;


import ExcelExport.ReadExcelFile;
import Operation.ReadObject;
import Operation.UIOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.Properties;

public class ExecuteTest {

    @Test
    public void TestLogin(){

        System.out.println(System.getProperty("user.dir"));
        System.setProperty("webdriver.gecko.driver", "/home/ashwin/Desktop/geckodriver");
        WebDriver driver = new FirefoxDriver();
        ReadExcelFile file = new ReadExcelFile();
        ReadObject object = new ReadObject();
        Properties allObjects = object.getObjectRepository();
        UIOperation operation = new UIOperation();

        Sheet sheet = file.ReadExcel(System.getProperty("user.dir")+"/Keyword_Driven_FrameWork/","TestCase.xlsx","KeywordFramework");

        //Read number of rows in excel
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        for(int i=0; i < rowCount+1; i++){
            Row row = sheet.getRow(i);
            if(row.getCell(0).toString().length() == 0){
                System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
                        row.getCell(3).toString()+"----"+ row.getCell(4).toString());

                operation.Perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
                        row.getCell(3).toString(), row.getCell(4).toString());
            }
            else{
                //Print the new testcase name when it started
                System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
            }
        }

    }
}
