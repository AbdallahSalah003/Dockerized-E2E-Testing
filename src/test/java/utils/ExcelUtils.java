package utils;
import data.SearchData;
import data.TransactionData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {

    public static List<TransactionData> readTransactionData(String filePath) {
        List<TransactionData> transactionDataList = new ArrayList<>();

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

                TransactionData data = new TransactionData(gender, firstname, lastname, email, phone, password, searchQuery, filterQuery, product,
                        quantity, country, zone, postcode, coupon, address);
                transactionDataList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactionDataList;
    }
	public static List<SearchData> readSearchData(String filepath) {
		List<SearchData> searchDataList = new ArrayList<>();
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

				SearchData data = new SearchData(email, password, searchQuery);
				searchDataList.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return searchDataList;
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
