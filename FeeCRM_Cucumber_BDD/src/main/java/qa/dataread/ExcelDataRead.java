package qa.dataread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import qa.base.BasePage;
import qa.base.FrameworkConstants;
import qa.utils.LoggerFile;

public class ExcelDataRead {

	Logger log = LoggerFile.getLogger(BasePage.class);

	/*
	 * Method Name : Xls_Reader() Description : Parameterized Constructor Used to
	 * identify the Location of EXCEL files
	 */
	// @DataProvider(name = "getXlsData")
	public Object[][] dataSupplier(String methodName) throws IOException {
		log.info("Excel Data Read started");

		// String methodName = m.getName();

		String dataFileLocation = FrameworkConstants.projectPath + "\\TestData\\cpq\\" + methodName + ".xlsx";
		log.info("Excel file: " + dataFileLocation);
		File file = new File(dataFileLocation);
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		wb.close();
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			HashMap<String, String> datamap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
			}
			obj[i][0] = datamap;

		}

		return obj;

	}
	
	public Object[][] dataSupplierPath(String methodName, String filePath) throws IOException {
		log.info("Excel Data Read started");

		// String methodName = m.getName();

		//String dataFileLocation = FrameworkConstants.projectPath + "\\TestData\\cpq\\" + methodName + ".xlsx";
		log.info("Excel file: " + filePath);
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		wb.close();
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			HashMap<String, String> datamap = new HashMap<>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
			}
			obj[i][0] = datamap;

		}

		return obj;

	}

	// Get Data for specified row from Data Sheet
	public static ArrayList<Map<String, String>> getRowData(String filepath, String sheet, int row) {
		ArrayList<Map<String, String>> records = new ArrayList<>();
		int columnCount = ExcelDataRead.getColumnCount(filepath, sheet);
		ArrayList<String> headers = new ArrayList<>();
		for (int i = 0; i <= columnCount; i++) {
			headers.add(ExcelDataRead.getCellValue(filepath, sheet, 0, i));
		}
		Map<String, String> datas = new HashMap<>();
		for (int j = 0; j < columnCount; j++) {
			datas.put(headers.get(j).toString(), ExcelDataRead.getCellValue(filepath, sheet, row, j));
		}
		records.add(datas);

		return records;
	}

	public static String getCellValue(String path, String sheet, int r, int c) {
		String v = "";
		try {

			Workbook wb = WorkbookFactory.create(new FileInputStream(path));
			Cell cell = wb.getSheet(sheet).getRow(r).getCell(c);
			v = cell.toString();

		} catch (Exception e) {
			System.err.println("Exception in reading cell");
			e.printStackTrace();
		}
		return v;
	}

	// Get number of Columns present in Excel
	public static int getColumnCount(String path, String sheet) {
		int c = 0;
		try {

			c = WorkbookFactory.create(new FileInputStream(path)).getSheet(sheet).getRow(0).getLastCellNum();
		} catch (Exception e) {
			System.err.println("Exception:  Get number of Columns present in Excel");
			e.printStackTrace();
		}
		return c;
	}

	public static String getValue(ArrayList<Map<String, String>> record, String header) {
		String value = null;
		try {

			for (Map<String, String> keyValue : record) {
				if (keyValue.containsKey(header)) {
					value = keyValue.get(header);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	// Read Data
	private static XSSFSheet getSheet(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		return sheet;
	}

	public static Map<String, Map<String, String>> getExcelAsMap(String path, String sheetName) throws IOException {
		XSSFSheet sheet = getSheet(path, sheetName);
		Map<String, Map<String, String>> completeSheetData = new HashMap<String, Map<String, String>>();
		List<String> columnHeader = new ArrayList<String>();
		Row row = sheet.getRow(0);
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			columnHeader.add(cellIterator.next().getStringCellValue());
		}
		sheet.getLastRowNum();
		int rowCount = sheet.getLastRowNum();
		int columnCount = row.getLastCellNum();
		for (int i = 1; i <= rowCount; i++) {
			Map<String, String> singleRowData = new HashMap<String, String>();
			Row row1 = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				Cell cell = row1.getCell(j);
				singleRowData.put(columnHeader.get(j), getCellValueAsString(cell));
			}
			completeSheetData.put(String.valueOf(i), singleRowData);
		}
		return completeSheetData;
	}

	public static String getCellValueAsString(Cell cell) {
		String cellValue = null;
		switch (cell.getCellType()) {
		case NUMERIC:
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case STRING:
			cellValue = cell.getStringCellValue();
			break;
		case BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA:
			cellValue = cell.getCellFormula();
		case BLANK:
			cellValue = "BLANK";
		default:
			cellValue = "DEFAULT";
		}
		return cellValue;
	}

}
