package utils;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.math.BigDecimal;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.List;
	 
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.CellType;
	import org.apache.poi.ss.usermodel.FormulaEvaluator;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.bean.menu;
import model.bean.user;

	 
	public class ReadExcelNhanVien {
		public static final int COLUMN_INDEX_ID         = 0;
	    public static final int COLUMN_INDEX_Name      = 1;
	    public static final int COLUMN_INDEX_Email      = 2;

	 
	    public static void main(String[] args) throws IOException {
	        final String excelFilePath = "C:/demo/Nhanvien.xlsx";
	        final List<user> books = readExcel(excelFilePath);
	        for (user it : books) {
	            System.out.println(it);
	        }
	    }
	 
	    public static List<user> readExcel(String excelFilePath) throws IOException {
	        List<user> listBooks = new ArrayList<>();
	 
	        // Get file
	        InputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	        // Get workbook
	        Workbook workbook = getWorkbook(inputStream, excelFilePath);
	 
	        // Get sheet
	        Sheet sheet = workbook.getSheetAt(0);
	 
	        // Get all rows
	        Iterator<Row> iterator = sheet.iterator();
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            if (nextRow.getRowNum() == 0) {
	                // Ignore header
	                continue;
	            }
	 
	            // Get all cells
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	 
	            // Read cells and set value for book object
	            user book = new user();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                Object cellValue = getCellValue(cell);
	                if (cellValue == null || cellValue.toString().isEmpty()) {
	                    continue;
	                }
	                // Set value for book object
	                int columnIndex = cell.getColumnIndex();
	                switch (columnIndex) {
	                case COLUMN_INDEX_ID:
	                    book.setId(0);
	                    break;
	                case COLUMN_INDEX_Name  :
	                    book.setName((String) getCellValue(cell));;
	                    break;
	                case COLUMN_INDEX_Email:
	                    book.setEmail((String) getCellValue(cell));
	                    break;
	                
	                default:
	                    break;
	                }
	 
	            }
	            listBooks.add(book);
	        }
	 
	        workbook.close();
	        inputStream.close();
	 
	        return listBooks;
	    }
	 
	    // Get Workbook
	    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
	        Workbook workbook = null;
	        if (excelFilePath.endsWith("xlsx")) {
	            workbook = new XSSFWorkbook(inputStream);
	        } else if (excelFilePath.endsWith("xls")) {
	            workbook = new HSSFWorkbook(inputStream);
	        } else {
	            throw new IllegalArgumentException("The specified file is not Excel file");
	        }
	 
	        return workbook;
	    }
	 
	    // Get cell value
	    private static Object getCellValue(Cell cell) {
	        CellType cellType = cell.getCellTypeEnum();
	        Object cellValue = null;
	        switch (cellType) {
	        case BOOLEAN:
	            cellValue = cell.getBooleanCellValue();
	            break;
	        case FORMULA:
	            Workbook workbook = cell.getSheet().getWorkbook();
	            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
	            cellValue = evaluator.evaluate(cell).getNumberValue();
	            break;
	        case NUMERIC:
	            cellValue = cell.getNumericCellValue();
	            break;
	        case STRING:
	            cellValue = cell.getStringCellValue();
	            break;
	        case _NONE:
	        case BLANK:
	        case ERROR:
	            break;
	        default:
	            break;
	        }
	 
	        return cellValue;
	    }
	}

