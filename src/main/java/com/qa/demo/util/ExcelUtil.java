package com.qa.demo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.qa.demo.base.BasePage;

public class ExcelUtil extends BasePage {

	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "\\resources\\testdata\\data.xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				if ((k == 2) || (k == 3)|| (k == 4)|| (k == 6) || (k == 7)) {
					data[i][k] = (int)sheet.getRow(i + 1).getCell(k).getNumericCellValue();
				} else {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}
//				 System.out.println("Value="+data[i][k]+"\t DataType="+data[i][k].getClass().getSimpleName()+"\t i:"+i+"\t k:"+k);
			}
		}
//		for (int i = 0; i < data.length; ++i) {
//            for(int j = 0; j < data[i].length; ++j) {
//                System.out.println(data[i][j]);
//            }
//        }
//		Object[][] data= {
//				{"batman1", "ironman1"		},
//				{"batman2", "ironman2"		},
//				{"batman3", "ironman3"		},
//		};
		return data;
	}
}