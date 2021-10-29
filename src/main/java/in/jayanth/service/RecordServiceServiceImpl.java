package in.jayanth.service;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.aggregates.WorksheetProtectionBlock;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import in.jayanth.model.Records;
import in.jayanth.repository.RecordsRepository;

@Service
public class RecordServiceServiceImpl implements RecordsService {

	@Autowired
	private RecordsRepository RecordsRepository;
	
	@Override
	public List<Records> getAllRecords() {
		List<Records> findAll = RecordsRepository.findAll();
		return findAll;
	}

	@Override
	public boolean createPdf(List<Records> records, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		
		Document document=new Document(PageSize.A4,15, 15, 45, 30);
		
		try {
			String filePath=context.getRealPath("/resources/reports");
			File file=new File(filePath);
			boolean exists=new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			} 
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"records"+".pdf"));
			document.open();
			
			Font mainFont=FontFactory.getFont("Arial", 10,BaseColor.BLACK);
			Paragraph paragraph=new Paragraph("All Records",mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);
			
			
			PdfPTable table=new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			
			Font tableHeader=FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody=FontFactory.getFont("Arial", 9, BaseColor.BLACK);
			
			float[] columnWidths= {2f, 2f, 2f, 2f, 2f, 2f};
			table.setWidths(columnWidths);
			
			PdfPCell planId=new PdfPCell(new Paragraph("PLAN_ID",tableHeader));
			planId.setBorderColor(BaseColor.BLACK);
			planId.setPaddingLeft(10);
			planId.setHorizontalAlignment(Element.ALIGN_CENTER);
			planId.setVerticalAlignment(Element.ALIGN_CENTER);
			planId.setBackgroundColor(BaseColor.GRAY);
			planId.setExtraParagraphSpace(5f);
			table.addCell(planId);
			
			PdfPCell planName=new PdfPCell(new Paragraph("PLAN_NAME",tableHeader));
			planName.setBorderColor(BaseColor.BLACK);
			planName.setPaddingLeft(10);
			planName.setHorizontalAlignment(Element.ALIGN_CENTER);
			planName.setVerticalAlignment(Element.ALIGN_CENTER);
			planName.setBackgroundColor(BaseColor.GRAY);
			planName.setExtraParagraphSpace(5f);
			table.addCell(planName);
			
			PdfPCell planStatus=new PdfPCell(new Paragraph("PLAN_STATUS",tableHeader));
			planStatus.setBorderColor(BaseColor.BLACK);
			planStatus.setPaddingLeft(10);
			planStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
			planStatus.setVerticalAlignment(Element.ALIGN_CENTER);
			planStatus.setBackgroundColor(BaseColor.GRAY);
			planStatus.setExtraParagraphSpace(5f);
			table.addCell(planStatus);
			
			PdfPCell planSdate=new PdfPCell(new Paragraph("PLAN_SDATE",tableHeader));
			planSdate.setBorderColor(BaseColor.BLACK);
			planSdate.setPaddingLeft(10);
			planSdate.setHorizontalAlignment(Element.ALIGN_CENTER);
			planSdate.setVerticalAlignment(Element.ALIGN_CENTER);
			planSdate.setBackgroundColor(BaseColor.GRAY);
			planSdate.setExtraParagraphSpace(5f);
			table.addCell(planSdate);
			
			PdfPCell planEdate=new PdfPCell(new Paragraph("PLAN_EDATE",tableHeader));
			planEdate.setBorderColor(BaseColor.BLACK);
			planEdate.setPaddingLeft(10);
			planEdate.setHorizontalAlignment(Element.ALIGN_CENTER);
			planEdate.setVerticalAlignment(Element.ALIGN_CENTER);
			planEdate.setBackgroundColor(BaseColor.GRAY);
			planEdate.setExtraParagraphSpace(5f);
			table.addCell(planEdate);
			
			PdfPCell benfitAmt=new PdfPCell(new Paragraph("BENFIT_AMOUNT",tableHeader));
			benfitAmt.setBorderColor(BaseColor.BLACK);
			benfitAmt.setPaddingLeft(10);
			benfitAmt.setHorizontalAlignment(Element.ALIGN_CENTER);
			benfitAmt.setVerticalAlignment(Element.ALIGN_CENTER);
			benfitAmt.setBackgroundColor(BaseColor.GRAY);
			benfitAmt.setExtraParagraphSpace(5f);
			table.addCell(benfitAmt);
			
			for (Records record : records) {
				PdfPCell planIdValue=new PdfPCell(new Paragraph(record.getPlanId().toString(),tableBody));
				planIdValue.setBorderColor(BaseColor.BLACK);
				planIdValue.setPaddingLeft(10);
				planIdValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				planIdValue.setVerticalAlignment(Element.ALIGN_CENTER);
				planIdValue.setBackgroundColor(BaseColor.GRAY);
				planIdValue.setExtraParagraphSpace(5f);
				table.addCell(planIdValue);	
				
				PdfPCell planNameValue=new PdfPCell(new Paragraph(record.getPlanName(),tableBody));
				planNameValue.setBorderColor(BaseColor.BLACK);
				planNameValue.setPaddingLeft(10);
				planNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				planNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
				planNameValue.setBackgroundColor(BaseColor.GRAY);
				planNameValue.setExtraParagraphSpace(5f);
				table.addCell(planNameValue);	
				
				PdfPCell planStatusValue=new PdfPCell(new Paragraph(record.getPlanStatus(),tableBody));
				planStatusValue.setBorderColor(BaseColor.BLACK);
				planStatusValue.setPaddingLeft(10);
				planStatusValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				planStatusValue.setVerticalAlignment(Element.ALIGN_CENTER);
				planStatusValue.setBackgroundColor(BaseColor.GRAY);
				planStatusValue.setExtraParagraphSpace(5f);
				table.addCell(planStatusValue);	
				
				PdfPCell planSdateValue=new PdfPCell(new Paragraph(record.getPlanSdate().toString(),tableBody));
				planSdateValue.setBorderColor(BaseColor.BLACK);
				planSdateValue.setPaddingLeft(10);
				planSdateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				planSdateValue.setVerticalAlignment(Element.ALIGN_CENTER);
				planSdateValue.setBackgroundColor(BaseColor.GRAY);
				planSdateValue.setExtraParagraphSpace(5f);
				table.addCell(planSdateValue);
				
				PdfPCell planEdateValue=new PdfPCell(new Paragraph(record.getPlanEdate().toString(),tableBody));
				planEdateValue.setBorderColor(BaseColor.BLACK);
				planEdateValue.setPaddingLeft(10);
				planEdateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				planEdateValue.setVerticalAlignment(Element.ALIGN_CENTER);
				planEdateValue.setBackgroundColor(BaseColor.GRAY);
				planEdateValue.setExtraParagraphSpace(5f);
				table.addCell(planEdateValue);
				
				PdfPCell benfitAmtValue=new PdfPCell(new Paragraph(record.getBenfitAmt().toString(),tableBody));
				benfitAmtValue.setBorderColor(BaseColor.BLACK);
				benfitAmtValue.setPaddingLeft(10);
				benfitAmtValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				benfitAmtValue.setVerticalAlignment(Element.ALIGN_CENTER);
				benfitAmtValue.setBackgroundColor(BaseColor.GRAY);
				benfitAmtValue.setExtraParagraphSpace(5f);
				table.addCell(benfitAmtValue);
			}
			document.add(table);
			document.close();
			writer.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	
	}

	@Override
	public boolean createExcel(List<Records> records, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		
		String filePath=context.getRealPath("/resources/reports");
		File file=new File(filePath);
		boolean exists=new File(filePath).exists();
		if (!exists) {
			new File(filePath).mkdirs();
		} 
		try {
			
			FileOutputStream outputStream =new FileOutputStream(file+"/"+"records"+".xls");
			HSSFWorkbook workBook=new HSSFWorkbook();
			HSSFSheet workSheet=workBook.createSheet("records");
			workSheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle=workBook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			
			HSSFRow headRow=workSheet.createRow(0);
			
			HSSFCell planId=headRow.createCell(0);
			planId.setCellValue("PLAN_ID");
			planId.setCellStyle(headerCellStyle);
			
			HSSFCell planName=headRow.createCell(1);
			planName.setCellValue("PLAN_NAME");
			planName.setCellStyle(headerCellStyle);
			
			HSSFCell planStatus=headRow.createCell(2);
			planStatus.setCellValue("PLAN_STATUS");
			planStatus.setCellStyle(headerCellStyle);
			
			HSSFCell planSdate=headRow.createCell(3);
			planSdate.setCellValue("PLAN_SDATE");
			planSdate.setCellStyle(headerCellStyle);
			
			HSSFCell planEdate=headRow.createCell(4);
			planEdate.setCellValue("PLAN_EDATE");
			planEdate.setCellStyle(headerCellStyle);
			
			HSSFCell benfitAmt=headRow.createCell(5);
			benfitAmt.setCellValue("BENFIT_AMOUNT");
			benfitAmt.setCellStyle(headerCellStyle);
			
			int i=1;
			for (Records record : records) {
				
				HSSFRow bodyRow=workSheet.createRow(i);
				
				HSSFCellStyle bodyCellStyle=workBook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
				
				HSSFCell planIdValue=bodyRow.createCell(0);
				planIdValue.setCellValue(record.getPlanId());
				planIdValue.setCellStyle(bodyCellStyle);
				
				HSSFCell planNameValue=bodyRow.createCell(1);
				planNameValue.setCellValue(record.getPlanName());
				planNameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell planStatusValue=bodyRow.createCell(2);
				planStatusValue.setCellValue(record.getPlanStatus());
				planStatusValue.setCellStyle(bodyCellStyle);
				
				HSSFCell planSdateValue=bodyRow.createCell(3);
				planSdateValue.setCellValue(record.getPlanSdate());
				planSdateValue.setCellStyle(bodyCellStyle);
				
				HSSFCell planEdateValue=bodyRow.createCell(4);
				planEdateValue.setCellValue(record.getPlanEdate());
				planEdateValue.setCellStyle(bodyCellStyle);
				
				HSSFCell benfitAmtValue=bodyRow.createCell(5);
				benfitAmtValue.setCellValue(record.getBenfitAmt());
				benfitAmtValue.setCellStyle(bodyCellStyle);
				
				i++;
				
			}
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

}
