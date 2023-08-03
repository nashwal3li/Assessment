package Tasks;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    public static Object[][] getTestData(String filePath, String sheetName){
        FileInputStream file = null;
        XSSFWorkbook workbook = null;
        try {
            file = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];

        // Read Data & Store it in an array

        for(int i=0; i < rowCount ;i++){
            XSSFRow row = sheet.getRow(i+1);
            for(int j=0; j < colCount ;j++){
                XSSFCell cell = row.getCell(j);
                data[i][j] = cell.toString();
            }
        }

        return data;
    }
}
