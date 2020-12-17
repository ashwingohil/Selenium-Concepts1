import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadingDataFrom {

    public void ReadExcel(String filepath, String filename, String sheetname) throws IOException {

        try {
            File file = new File(filepath + filename);
            FileInputStream istream = new FileInputStream(file);

            Workbook myWorkBook = null;
            String fileExtension = filename.substring(filename.indexOf("."));

            if (fileExtension.equals(".xlsx")) {
                myWorkBook = new XSSFWorkbook(istream);
            } else if (fileExtension.equals(".xls")) {
                myWorkBook = new HSSFWorkbook(istream);
            }

            Sheet mySheet = myWorkBook.getSheet(sheetname);

            //Get Row Count
            int rowCount = mySheet.getLastRowNum() - mySheet.getFirstRowNum();
            for (int i = 0; i < rowCount + 1; i++) {
                Row row = mySheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    System.out.print(row.getCell(j).getStringCellValue() + "||");
                }
                System.out.println();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public static void main(String[] args){

        ReadingDataFrom object = new ReadingDataFrom();

        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        dir = dir+"/Data_Driven_FrameWork/";
        String filename = "NameAddress.xlsx";
        String sheetname = "NameAddress";

        try {
            object.ReadExcel(dir, filename, sheetname);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
