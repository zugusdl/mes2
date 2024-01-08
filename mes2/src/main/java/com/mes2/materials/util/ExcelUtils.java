package com.mes2.materials.util;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.PurchaseDTO;

public class ExcelUtils {
	 public static Workbook createWorkbook() {
	        return new XSSFWorkbook();
	    }

	 public static void addDataToWorkbook(Workbook workbook, List<PurchaseDTO> data) {
	        Sheet sheet = workbook.createSheet("Purchase Data");

	        Row headerRow = sheet.createRow(0);
	        String[] columns = {"orders_code", "product_code", "name", "cost", "price", "category", "quantity", "regdate", "status"};
	        for (int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	        }

	        int rowNum = 1;
	        for (PurchaseDTO purchase : data) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(purchase.getOrders_code());
	            row.createCell(1).setCellValue(purchase.getProduct_code());
	            row.createCell(2).setCellValue(purchase.getName());
	            row.createCell(3).setCellValue(purchase.getCost());
	            row.createCell(4).setCellValue(purchase.getPrice());
	            row.createCell(5).setCellValue(purchase.getCategory());
	            row.createCell(6).setCellValue(purchase.getQuantity());
	            row.createCell(7).setCellValue(purchase.getRegdate());
	            row.createCell(8).setCellValue(purchase.getStatus());
	        }
	    }


}
	   
	

