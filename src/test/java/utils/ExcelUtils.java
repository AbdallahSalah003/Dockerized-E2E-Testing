package utils;
import data.TC002_Data;
import data.TC001_Data;
import data.TC003_Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    public static List<TC001_Data> readTC001Data(String filePath) {
        List<TC001_Data> transactionDataList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

				String gender = getCellValueAsString(cellIterator.next());
				String firstname = getCellValueAsString(cellIterator.next());
				String lastname = getCellValueAsString(cellIterator.next());
                String email = getCellValueAsString(cellIterator.next());
                String password = getCellValueAsString(cellIterator.next());
				String phone = getCellValueAsString(cellIterator.next());
                String searchQuery = getCellValueAsString(cellIterator.next());
                String filterQuery = getCellValueAsString(cellIterator.next());
                String product = getCellValueAsString(cellIterator.next());
                String quantity = getCellValueAsString(cellIterator.next()).replace(".0","");
                String country = getCellValueAsString(cellIterator.next());
                String zone = getCellValueAsString(cellIterator.next());
                String postcode = getCellValueAsString(cellIterator.next()).replace(".0","");
                String coupon = getCellValueAsString(cellIterator.next()).replace(".0","");
                String address = getCellValueAsString(cellIterator.next());

                TC001_Data data = new TC001_Data(gender, firstname, lastname, email, phone, password, searchQuery, filterQuery, product,
                        quantity, country, zone, postcode, coupon, address);
                transactionDataList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactionDataList;
    }
	public static List<TC002_Data> readTC002Data(String filepath) {
		List<TC002_Data> searchDataList = new ArrayList<>();
		try (FileInputStream file = new FileInputStream(filepath);
			 Workbook workbook = new XSSFWorkbook(file)) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				String email = getCellValueAsString(cellIterator.next());
				String password = getCellValueAsString(cellIterator.next());
				String searchQuery = getCellValueAsString(cellIterator.next());

				TC002_Data data = new TC002_Data(email, password, searchQuery);
				searchDataList.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return searchDataList;
	}
	public static List<TC003_Data> readTC003Data(String filepath) {
		List<TC003_Data> dataList = new ArrayList<>();
		try (FileInputStream file = new FileInputStream(filepath);
			 Workbook workbook = new XSSFWorkbook(file)) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				String email = getCellValueAsString(cellIterator.next());
				String password = getCellValueAsString(cellIterator.next());
				String product = getCellValueAsString(cellIterator.next());

				TC003_Data data = new TC003_Data(email, password, product);
				dataList.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataList;
	}
    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        return cell.getStringCellValue();
                    case NUMERIC:
                        return String.valueOf(cell.getNumericCellValue());
                    case BOOLEAN:
                        return String.valueOf(cell.getBooleanCellValue());
                    default:
                        return "";
                }
            case BLANK:
                return "";
            default:
                return "";
        }
    }
}
