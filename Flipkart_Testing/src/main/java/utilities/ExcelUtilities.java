package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import baseClass.Base_Library;

public class ExcelUtilities extends Base_Library {
	/*
	 * public String excelRead(String sheet, int RowNmber, int CellNumber) throws
	 * IOException { File path = new
	 * File("src/test/resources/TestData/FlipkartTestData.xlsx"); //select path
	 * 
	 * FileInputStream reader = new FileInputStream(path); // to read in excel we
	 * user fileInputStream
	 * 
	 * XSSFWorkbook book = new XSSFWorkbook(reader); // workbook reference created
	 * of current sheet
	 * 
	 * XSSFSheet sheets = book.getSheet(sheet); // for sheet selection
	 * 
	 * XSSFRow row = sheets.getRow(RowNmber); // for row selection
	 * 
	 * XSSFCell cells = row.getCell(CellNumber); // for cell selection
	 * 
	 * return cells.getStringCellValue(); }
	 */

	//reading excel data
	public Map<String, String> getRowData(String sheetName, int rowNumber) throws IOException {

		File path = new File("src/test/resources/TestData/FlipkartTestData.xlsx");

		FileInputStream fis = new FileInputStream(path);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		Sheet sheet = workbook.getSheet(sheetName);

		Row headerRow = sheet.getRow(0);
		Row dataRow = sheet.getRow(rowNumber);
		// logger.info("***************Excel Reading****************");

		Map<String, String> data = new HashMap<>();

		DataFormatter formatter = new DataFormatter();

		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			Cell headerCell = headerRow.getCell(i);
			Cell valueCell = dataRow.getCell(i);

			String key = formatter.formatCellValue(headerCell);
			String value = formatter.formatCellValue(valueCell);

			data.put(key, value);
		}

		workbook.close();
		fis.close();

		return data;
	}

	//writing back in excel
	public void writeData(String sheetName, int rowNumber, int cellNumber, String value) throws IOException{

		File path = new File("src/test/resources/TestData/FlipkartTestData.xlsx");
		FileInputStream fis = new FileInputStream(path);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		
		Row row = sheet.getRow(rowNumber);
		if(row==null) {
			row = sheet.createRow(rowNumber);
		}
		
		Cell cell = row.getCell(cellNumber);
		if(cell==null) {
			cell=row.createCell(cellNumber);
		}
		
		cell.setCellValue(value);
		fis.close();
		
		FileOutputStream fos = new FileOutputStream(path);
		workbook.write(fos);
		
		workbook.close();
		fos.close();
		
	}

}
