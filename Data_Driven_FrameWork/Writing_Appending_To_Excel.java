import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Scanner;

public class Writing_Appending_To_Excel {

    public void AppendToExcel(String filename, String filepath, String sheetname){

        //https://docs.oracle.com/javase/7/docs/api/java/io/File.html

        File file = new File(filepath+"/"+filename);

        //https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html

        try{

            FileInputStream istream = new FileInputStream(file);

            //We need WorkBook, Sheet, Row, Cell
            Workbook myWorkBook = null;

            //Workbook - https://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/Workbook.html
            //Implementing class - https://poi.apache.org/apidocs/dev/org/apache/poi/hssf/usermodel/HSSFWorkbook.html
            String fileExtension = filename.substring(filename.indexOf("."));

            if(fileExtension.equals(".xlsx")){
                try{
                    myWorkBook = new XSSFWorkbook(istream);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }else if(fileExtension.equals(".xls")){
                try{
                    myWorkBook = new HSSFWorkbook(istream);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }

            //getSheet() of WorkBook interface whose implementing class is XSSF or HSSF throws Sheet object
            Sheet sheetObject = myWorkBook.getSheet(sheetname);
            int rowCount = sheetObject.getLastRowNum()-sheetObject.getFirstRowNum();
            Row row = sheetObject.getRow(0);

            //Now Row interface and implementing class
            //https://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/Row.html
            //https://poi.apache.org/apidocs/dev/org/apache/poi/hssf/usermodel/HSSFRow.html
            //We have to get the Row and get the Cell

            Row newRow = sheetObject.createRow(rowCount+1);
            //Edit this value over here to see the changes
            String[] dataToWrite = {"Mr. G", "Rajasthan"};

            for(int j=0; j<row.getLastCellNum(); j++) {
                Cell myCell = newRow.createCell(j);
                myCell.setCellValue(dataToWrite[j]);
            }

            istream.close();

            FileOutputStream outputStream = new FileOutputStream(file);
            //write data in the excel file
            myWorkBook.write(outputStream);
            //close output stream
            outputStream.close();

        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){

        Writing_Appending_To_Excel object = new Writing_Appending_To_Excel();
        //https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html

        String filepath = System.getProperty("user.dir");
        System.out.println(filepath);

        String workingfolder = "Data_Driven_FrameWork";
        filepath = filepath + "/"+workingfolder;

        String filename = "NameAddress.xlsx";
        String sheetname = "NameAddress";

        object.AppendToExcel(filename, filepath, sheetname);

    }
}
