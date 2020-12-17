package ExcelExport;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


//https://poi.apache.org/apidocs/dev/org/apache/poi/hssf/usermodel/HSSFWorkbook.html
//https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html
//https://docs.oracle.com/javase/7/docs/api/java/io/File.html
//https://poi.apache.org/apidocs/dev/org/apache/poi/xssf/usermodel/XSSFWorkbook.html

public class ReadExcelFile {

    public Sheet ReadExcel(String filepath, String filename, String sheetname){

        Sheet sheet = null;
        try{

            File file = new File(filepath+"/"+filename);
            FileInputStream istream = new FileInputStream(file);
            Workbook myworkbook = null;

            String extension = filename.substring(filename.indexOf("."));
            if(extension.equals(".xlsx")){
                myworkbook = new XSSFWorkbook(istream);
            }else if(extension.equals(".xls")){
                myworkbook = new HSSFWorkbook(istream);
            }

            sheet = myworkbook.getSheet(sheetname);

        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return sheet;
    }
}
