package inventory.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import inventory.model.Invoice;
import inventory.util.Constant;
import inventory.util.DateUtils;

public class InvoiceReport extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Invoice> invoices = (List<Invoice>) model.get(Constant.KEY_GOODS_RECEIPT_REPORT);
		String fileName = "";
		if (invoices.get(0).getType() == Constant.TYPE_GOODS_RECEIPT) {
			fileName = "goods-receipts-" + System.currentTimeMillis() + ".xlsx";
		} else {
			fileName = "goods-issues-" + System.currentTimeMillis() + ".xlsx";
		}
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
		Sheet sheet = workbook.createSheet("data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("#");
		header.createCell(1).setCellValue("Code");
		header.createCell(2).setCellValue("Quantity");
		header.createCell(3).setCellValue("Price");
		header.createCell(4).setCellValue("Product");
		header.createCell(5).setCellValue("Update date");

		int rownum = 1;
		for (Invoice invoice : invoices) {
			Row row = sheet.createRow(rownum++);
			row.createCell(0).setCellValue(rownum - 1);
			row.createCell(1).setCellValue(invoice.getCode());
			row.createCell(2).setCellValue(invoice.getQty());
			row.createCell(3).setCellValue(invoice.getPrice().toString());
			row.createCell(4).setCellValue(invoice.getProductInfo().getName());
			row.createCell(5).setCellValue(DateUtils.dateToString(invoice.getUpdateDate()));
		}
	}

}
