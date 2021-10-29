package in.jayanth.controller;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.jayanth.model.Records;
import in.jayanth.service.RecordsService;

@Controller
public class PdfExcelController {

	@Autowired
	private RecordsService recordsService;

	@Autowired
	private ServletContext context;

	@GetMapping(value = "/")
	public String allRecords(Model model) {

		List<Records> records = recordsService.getAllRecords();
		model.addAttribute("records", records);
		return "view/records";
	}

	@GetMapping(value ="/createPdf")
	public void createPdf(HttpServletRequest request, HttpServletResponse response) {

		List<Records> records = recordsService.getAllRecords();
		boolean isFlag = recordsService.createPdf(records, context, request, response);

		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"records"+".pdf");
			filedownload(fullPath, response, "records.pdf");
		}

	}
	
	@GetMapping(value ="/createExcel")
	public void createExcep(HttpServletRequest request, HttpServletResponse response) {
		List<Records> records = recordsService.getAllRecords();
		boolean isFlag = recordsService.createExcel(records, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"records"+".xls");
			filedownload(fullPath, response, "records.xls");
		}
		
	}
	
	

	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4069;
		if (file.exists()) {
		}
		try {
			FileInputStream inputStream = new FileInputStream(file);
			String mineType = context.getMimeType(fullPath);
			response.setContentType(mineType);
			response.setHeader("content-disposition", "attachment; filename="+fileName);
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outputStream.close();
			file.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}