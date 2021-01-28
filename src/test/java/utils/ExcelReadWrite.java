package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReadWrite {

    private static String Data;

    public static Object[][] getAllData(String excelFileName, String sheetName) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(new File("/Users/abakashpokharel/IdeaProjects/" + excelFileName + ".xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);


            //get the number of rows
            int rowCount = sheet.getLastRowNum();

            // get the number of columns
            int columnCount = sheet.getRow(0).getLastCellNum();
            data = new String[rowCount][columnCount];

            for (int i = 1; i < rowCount + 1; i++) {
                try {
                    XSSFRow row = sheet.getRow(i);
                    for (int j = 0; j < columnCount; j++) { // loop through the columns
                        try {
                            String cellValue = "";
                            try {
                                cellValue = row.getCell(j).getStringCellValue();
                            } catch (NullPointerException e) {

                            }

                            data[i - 1][j] = cellValue; // add to the data array
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fis.close();
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        Object[][] data = getAllData("DashboardExcelFile","Dashboard Data");
        //System.out.println("Valid UserName ::::;"+data[0][0]);
        //System.out.println("Valid Password:::"+data[0][1]);

        for(int i= 0 ; i< data.length ; i++){
           // System.out.println("Menus are :"+data[i][0]);

            for(int j = 0 ; j< data[i].length ; j++){
                System.out.println("Menus are :"+data[i][j]);
                if(j % 2 == 0){
                    System.out.println("Enter UserName : "+data[i][j]);
                }else {
                    System.out.println("Enter Password : "+data[i][j]);
                }
            }
            System.out.println("Click Signin Button");
        }

    }

}


//Workbook : XSSFWorkbook > HSSFWorkbook
// xssfworkbook > .xlsx
// hssfworkbook > .xls
// Sheet  : xssfSheet .xlsx
//hssfSheet > .xls

//Row : .xlsx > XSSFRow
// .xls > HSSFRow

//Column

// Cell

