package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {




	@SuppressWarnings("resource")
	public ArrayList<String> getData(String testCase,String sheetName) throws IOException
	{
		
		ArrayList<String> a = new ArrayList<>();
		
		String filePath = ".\\data\\TestData.xlsx";
		FileInputStream fis = new FileInputStream(filePath);

		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		int sheetCount = workBook.getNumberOfSheets();
		System.out.println("Number of Excel Sheet: " + sheetCount);

		for (int i = 0; i < sheetCount; i++) {
			if (workBook.getSheetName(i).equalsIgnoreCase(sheetName)) 
			{

				XSSFSheet sheet = workBook.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();

				Iterator<Cell> cell = firstRow.cellIterator();
				int testCasecoloumnIndex = 0;

				while (cell.hasNext()) {
					Cell cellValue = cell.next();

					if (cellValue.getStringCellValue().equalsIgnoreCase("TestCases")) 
					{
						System.out.println("TestCase coloum Header: " + cellValue);
						System.out.println("TestCase Coloumn Header Index: " + testCasecoloumnIndex);
						testCasecoloumnIndex = cellValue.getColumnIndex();

					}

				}


				while(rows.hasNext())
				{
					Row r = rows.next();
					if(r.getCell(testCasecoloumnIndex).getStringCellValue().equalsIgnoreCase(testCase))
					{
						Iterator<Cell> c = r.cellIterator();
						while(c.hasNext())
						{
							Cell cellValue = c.next();
							if(cellValue.getCellType()==CellType.STRING)
							{
								a.add(cellValue.getStringCellValue());
							}
							else
							{
								String numbericValue = NumberToTextConverter.toText(cellValue.getNumericCellValue()); //NumberToTextConverter method to convert numberic value to text
								a.add(numbericValue);
							}
							 
						}
					}

				}

			}

		}
		return a;
		
	}
	
}



