package resources;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelFile extends Base{

        public String readExcel(int i) throws IOException{
            FileInputStream fis = new FileInputStream("xlFiles\\pepperfry.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(i);

             String as = row.getCell(0).getStringCellValue();
            return as;
    }




}
