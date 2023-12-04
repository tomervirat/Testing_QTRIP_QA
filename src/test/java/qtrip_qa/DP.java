package qtrip_qa;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DP {
    @DataProvider(name = "data-provider")
    public Object[][] dpMethod(Method m) throws IOException {
        String currentDir = System.getProperty("user.dir");
        System.out.println("-CURRENT=" + currentDir);
        String testDataExcelPath = currentDir + "/src/test/Resources/DatasetsforQTrip.xlsx";

        FileInputStream ExcelFile = new FileInputStream(testDataExcelPath);
        XSSFWorkbook workBook = new XSSFWorkbook(ExcelFile);
        XSSFSheet sheet = workBook.getSheet(m.getName());

        List<Object[]> testData = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Object[] rowData = new Object[row.getLastCellNum() - 1];
            for (int j = 1; j < row.getLastCellNum(); j++) {
                rowData[j - 1] = row.getCell(j).toString();
            }
            testData.add(rowData);
        }

        Object[][] data = new Object[testData.size()][];
        Iterator<Object[]> it = testData.iterator();
        int i = 0;
        while (it.hasNext()) {
            data[i++] = it.next();
        }
        ExcelFile.close();
        workBook.close();
        return data;
    }
}
